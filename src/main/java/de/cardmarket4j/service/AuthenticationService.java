package de.cardmarket4j.service;

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
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import de.cardmarket4j.entity.enumeration.HTTPMethod;

public class AuthenticationService {
	private static String join(Collection<?> s, String delimiter) {
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

		if (index > 0) {
			String urlParams = url.substring(index + 1);
			Map<String, String> args = parseQueryString(urlParams);

			for (Entry<String, String> k : args.entrySet()) {
				headerParams.put(k.getKey(), k.getValue());

			}
		}

		for (Entry<String, String> k : headerParams.entrySet())
			if (!k.getKey().equalsIgnoreCase("realm")) {
				encodedParams.put(URLEncoder.encode(k.getKey(), encode), k.getValue());

			}

		List<String> paramStrings = new ArrayList<>();

		for (Entry<String, String> parameter : encodedParams.entrySet()) {
			paramStrings.add(parameter.getKey() + "=" + parameter.getValue());

		}

		String paramString = URLEncoder.encode(join(paramStrings, "&"), encode).replaceAll("'", "%27");

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

	private Map<String, String> parseQueryString(String query) {
		Map<String, String> queryParameters = new TreeMap<>();
		String[] querySegments = query.split("&");
		for (String segment : querySegments) {
			String[] parts = segment.split("=");
			if (parts.length > 0) {
				String key = parts[0].replaceAll("\\?", " ").trim();
				String val = parts[1].trim();
				queryParameters.put(key, val);
			}
		}
		return queryParameters;
	}
}