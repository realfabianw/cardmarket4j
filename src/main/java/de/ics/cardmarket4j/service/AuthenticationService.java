package de.ics.cardmarket4j.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.enums.HTTPMethod;

public class AuthenticationService {
	private static Logger LOGGER = LoggerFactory.getLogger("AuthenticationService");
	public static String join(Collection<?> s, String delimiter) {
		StringBuilder builder = new StringBuilder();
		Iterator<?> iter = s.iterator();
		while (iter.hasNext()) {
			builder.append(iter.next());
			if (!iter.hasNext()) {
				break;
			}
			builder.append(delimiter);
		}
		return builder.toString();
	}

	private final String appToken;
	private final String appSecret;
	private final String accessToken;

	private final String accessTokenSecret;

	public AuthenticationService(String appToken, String appSecret, String accessToken, String accessTokenSecret) {
		this.appToken = appToken;
		this.appSecret = appSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessTokenSecret;
	}

	public String createOAuthSignature(String url, HTTPMethod method)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

		Map<String, String> headerParams;
		Map<String, String> encodedParams = new TreeMap<>();
		int index = url.indexOf('?');
		String signatureMethod = "HMAC-SHA1";
		String version = "1.0";
		String encode = "UTF-8";
		String nonce = "" + System.currentTimeMillis();
		String timestamp = "" + (System.currentTimeMillis() / 1000);
		String baseUri = (index > 0 ? url.substring(0, index) : url);
		String signatureKey = URLEncoder.encode(appSecret, encode) + "&" + URLEncoder.encode(accessTokenSecret, encode);

		headerParams = new TreeMap<>();
		headerParams.put("oauth_consumer_key", appToken);
		headerParams.put("oauth_token", accessToken);
		headerParams.put("oauth_nonce", nonce);
		headerParams.put("oauth_timestamp", timestamp);
		headerParams.put("oauth_signature_method", signatureMethod);
		headerParams.put("oauth_version", version);
		headerParams.put("realm", baseUri);

		String baseString = method + "&" + URLEncoder.encode(baseUri, encode) + "&";

//		if (index > 0) {
//			String urlParams = url.substring(index + 1);
//			LOGGER.trace("url params: {}", urlParams);
//			Map<String, String> args = parseQueryString(urlParams);
//
//			for (Entry<String, String> k : args.entrySet()) {
//				headerParams.put(k.getKey(), k.getValue());
//
//			}
//		}

		for (Entry<String, String> k : headerParams.entrySet())
			if (!k.getKey().equalsIgnoreCase("realm")) {
				encodedParams.put(URLEncoder.encode(k.getKey(), encode), k.getValue());

			}

		List<String> paramStrings = new ArrayList<>();

		for (Entry<String, String> parameter : encodedParams.entrySet()) {
			paramStrings.add(parameter.getKey() + "=" + parameter.getValue());

		}

		String paramString = URLEncoder.encode(join(paramStrings, "&"), encode).replaceAll("'", "%27");
		
		if (index > 0) {
			paramString += url.substring(index+1);
		}
		baseString += paramString;

		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret = new SecretKeySpec(signatureKey.getBytes(), mac.getAlgorithm());
		mac.init(secret);
		byte[] digest = mac.doFinal(baseString.getBytes());
		// String oAuthSignature = DatatypeConverter.printBase64Binary(digest);
		String oAuthSignature = Base64.getEncoder().encodeToString(digest);
		headerParams.put("oauth_signature", oAuthSignature);

		List<String> headerParamStrings = new ArrayList<>();

		for (Entry<String, String> parameter : headerParams.entrySet())
			headerParamStrings.add(parameter.getKey() + "=\"" + parameter.getValue() + "\"");

		String authHeader = "OAuth " + join(headerParamStrings, ", ");
		return authHeader;
	}

	/**
	 * @deprecated
	 * @param fullUrl
	 * @param httpMethod
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 */
	@Deprecated
	private String createOAuthSignatureDreprecated(String fullUrl, HTTPMethod httpMethod)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		String realm = fullUrl;
		String oauth_version = "1.0";
		String oauth_consumer_key = appToken;
		String oauth_token = accessToken;
		String oauth_signature_method = "HMAC-SHA1";
		String oauth_timestamp = "" + (System.currentTimeMillis() / 1000);
		String oauth_nonce = "" + System.currentTimeMillis();

		String encodedRequestURL = rawUrlEncode(fullUrl);

		String baseString = httpMethod + "&" + encodedRequestURL + "&";

		String paramString = "oauth_consumer_key=" + rawUrlEncode(oauth_consumer_key) + "&oauth_nonce="
				+ rawUrlEncode(oauth_nonce) + "&oauth_signature_method=" + rawUrlEncode(oauth_signature_method)
				+ "&oauth_timestamp=" + rawUrlEncode(oauth_timestamp) + "&oauth_token=" + rawUrlEncode(oauth_token)
				+ "&oauth_version=" + rawUrlEncode(oauth_version);

		baseString = baseString + rawUrlEncode(paramString);

		String signingKey = rawUrlEncode(appSecret) + "&" + rawUrlEncode(accessTokenSecret);

		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
		mac.init(secret);
		byte[] digest = mac.doFinal(baseString.getBytes());
		String oauth_signature = Base64.getEncoder().encodeToString(digest);

		String authorizationProperty = "OAuth " + "realm=\"" + realm + "\", " + "oauth_version=\"" + oauth_version
				+ "\", " + "oauth_timestamp=\"" + oauth_timestamp + "\", " + "oauth_nonce=\"" + oauth_nonce + "\", "
				+ "oauth_consumer_key=\"" + oauth_consumer_key + "\", " + "oauth_token=\"" + oauth_token + "\", "
				+ "oauth_signature_method=\"" + oauth_signature_method + "\", " + "oauth_signature=\"" + oauth_signature
				+ "\"";
		return authorizationProperty;
	}

	private Map<String, String> parseQueryString(String query) {
		Map<String, String> queryParameters = new TreeMap<>();
		String[] querySegments = query.split("&");
		for (String segment : querySegments) {
			LOGGER.trace(segment);
			String[] parts = segment.split("=");
			LOGGER.trace("{}", parts.length);
			if (parts.length > 0) {
				String key = parts[0].replaceAll("\\?", " ").trim();
				String val = parts[1].trim();
				queryParameters.put(key, val);
			}
		}
		return queryParameters;
	}

	public static String rawUrlEncode(String str) {
		try {
			return URLEncoder.encode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.warn("The given String ({}) couldn't be encoded.\n{}", str, e);
			return str;
		}
	}
}