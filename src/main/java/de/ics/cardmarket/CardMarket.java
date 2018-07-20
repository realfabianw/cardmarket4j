package de.ics.cardmarket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.util.Pair;

public class CardMarket {
	private static Logger LOGGER = LoggerFactory.getLogger("CardMarket");

	private static final String URI_BASE = "https://www.mkmapi.eu/ws/v2.0/output.json/";

	private static final String oAuthVersion = "1.0";
	private static final String oAuthSignatureMethod = "HMAC-SHA1";
	private static final String oAuthTimeStamp = Long.toString(System.currentTimeMillis() / 1000);
	private static final String oAuthNonce = Long.toString(System.currentTimeMillis());

	private final String appToken;
	private final String appSecret;
	private final String accessToken;
	private final String accessTokenSecret;

	/**
	 * Constructor. Fill parameters according to given MKM profile app parameters.
	 * 
	 * @param appToken
	 * @param appSecret
	 * @param accessToken
	 * @param accessSecret
	 */
	public CardMarket(String appToken, String appSecret, String accessToken, String accessSecret) {
		this.appToken = appToken;
		this.appSecret = appSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessSecret;
	}

	/**
	 * Encoding function. To avoid deprecated version, the encoding used is UTF-8.
	 * 
	 * @param urlToEncode
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String encode(String urlToEncode) throws UnsupportedEncodingException {
		return URLEncoder.encode(urlToEncode, "UTF-8");
	}

	private String createOAuthSignature(String url)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		String encodedRequestURL = encode(url);
		String baseString = "GET&" + encodedRequestURL + "&";
		String paramString = "oauth_consumer_key=" + encode(appToken) + "&" + "oauth_nonce=" + encode(oAuthNonce) + "&"
				+ "oauth_signature_method=" + encode(oAuthSignatureMethod) + "&" + "oauth_timestamp="
				+ encode(oAuthTimeStamp) + "&" + "oauth_token=" + encode(accessToken) + "&" + "oauth_version="
				+ encode(oAuthVersion);

		baseString = baseString + encode(paramString);

		String signingKey = encode(appSecret) + "&" + encode(accessTokenSecret);

		Mac mac = Mac.getInstance("HmacSHA1");
		SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
		mac.init(secret);
		byte[] digest = mac.doFinal(baseString.getBytes());
		return DatatypeConverter.printBase64Binary(digest); // Base64.encode(digest) ;
	}

	private String createAuthorizationProperty(String url)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		return "OAuth " + "realm=\"" + url + "\", " + "oauth_version=\"" + oAuthVersion + "\", " + "oauth_timestamp=\""
				+ oAuthTimeStamp + "\", " + "oauth_nonce=\"" + oAuthNonce + "\", " + "oauth_consumer_key=\"" + appToken
				+ "\", " + "oauth_token=\"" + accessToken + "\", " + "oauth_signature_method=\"" + oAuthSignatureMethod
				+ "\", " + "oauth_signature=\"" + createOAuthSignature(url) + "\"";
	}

	private String readStream(InputStream is) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
			StringBuffer stringBuffer = new StringBuffer();
			String errorLine;
			while ((errorLine = bufferedReader.readLine()) != null) {
				stringBuffer.append(errorLine);
			}
			bufferedReader.close();
			return stringBuffer.toString();
		} catch (NullPointerException | IOException e) {
			return "";
		}
	}

	/**
	 * Perform the request to given url with OAuth 1.0a API.
	 * 
	 * @param requestURL url to be requested. Ex.
	 *                   https://www.mkmapi.eu/ws/v1.1/products/island/1/1/false
	 * @return true if request was successfully executed. You can retrieve the
	 *         content with responseContent();
	 */
	public String request(String request) {
		String fullRequestUrl = URI_BASE + request;
		int responseCode = -2;
		String header = "";
		String content = "";
		String error = "";
		LOGGER.info("Requesting " + fullRequestUrl);

		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) new URL(fullRequestUrl).openConnection();

			connection.addRequestProperty("Authorization", createAuthorizationProperty(fullRequestUrl));
			connection.connect();

			// from here standard actions...
			// read response code... read input stream.... close connection...
			try {
				responseCode = connection.getResponseCode();
			} catch (IOException e) {
				responseCode = -1;
			}

			header = connection.getHeaderFields().toString();

			try {
				content = readStream(connection.getInputStream());
			} catch (IOException e1) {
				content = "";
			}
			readStream(connection.getErrorStream());
		} catch (IOException | InvalidKeyException | NoSuchAlgorithmException e2) {
			e2.printStackTrace();
		}

		LOGGER.info("Code: {}", responseCode);
		LOGGER.info("Header: {}", header);
		LOGGER.info("Content: {}", content);
		LOGGER.info("Error: {}", error);

		return "";
	}
}
