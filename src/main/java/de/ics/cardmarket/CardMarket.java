package de.ics.cardmarket;

import java.io.BufferedReader;
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

	private static final String oAuthVersion = "1.0";
	private static final String oAuthSignatureMethod = "HMAC-SHA1";
	private static final String oAuthTimeStamp = Long.toString(System.currentTimeMillis() / 1000);
	private static final String oAuthNonce = Long.toString(System.currentTimeMillis());

	private final String appToken;
	private final String appSecret;
	private final String accessToken;
	private final String accessTokenSecret;

	private Throwable lastError;
	private int lastCode;
	private String lastContent;

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

		this.lastError = null;
	}

	/**
	 * Encoding function. To avoid deprecated version, the encoding used is UTF-8.
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String encode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "UTF-8");
	}

	/**
	 * Get last Error exception.
	 * 
	 * @return null if no errors; instead the raised exception.
	 */
	public Throwable lastError() {
		return lastError;
	}

	private String createOAuthSignature(String requestURL)
			throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
		String encodedRequestURL = encode(requestURL);
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

	private String createAuthorizationProperty(String requestURL)
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		return "OAuth " + "realm=\"" + requestURL + "\", " + "oauth_version=\"" + oAuthVersion + "\", "
				+ "oauth_timestamp=\"" + oAuthTimeStamp + "\", " + "oauth_nonce=\"" + oAuthNonce + "\", "
				+ "oauth_consumer_key=\"" + appToken + "\", " + "oauth_token=\"" + accessToken + "\", "
				+ "oauth_signature_method=\"" + oAuthSignatureMethod + "\", " + "oauth_signature=\""
				+ createOAuthSignature(requestURL) + "\"";
	}

	/**
	 * Perform the request to given url with OAuth 1.0a API.
	 * 
	 * @param requestURL url to be requested. Ex.
	 *                   https://www.mkmapi.eu/ws/v1.1/products/island/1/1/false
	 * @return true if request was successfully executed. You can retrieve the
	 *         content with responseContent();
	 */
	public Pair<Integer, String> request(String requestURL) {
		lastError = null;
		lastCode = 0;
		lastContent = "";
		try {
			LOGGER.info("Requesting " + requestURL);

			HttpURLConnection connection = (HttpURLConnection) new URL(requestURL).openConnection();
			connection.addRequestProperty("Authorization", createAuthorizationProperty(requestURL));
			connection.connect();

			// from here standard actions...
			// read response code... read input stream.... close connection...

			lastCode = connection.getResponseCode();
			
			LOGGER.info(connection.getHeaderFields().toString());

			LOGGER.info("Response Code is " + lastCode);

			if (200 == lastCode || 401 == lastCode || 404 == lastCode) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						lastCode == 200 ? connection.getInputStream() : connection.getErrorStream()));
				StringBuffer sb = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}
				rd.close();
				lastContent = sb.toString();
			}

			return new Pair<Integer, String>(lastCode, lastContent);

		} catch (Exception e) {
			LOGGER.info("(!) Error while requesting " + requestURL);
			lastError = e;
		}
		return new Pair<Integer, String>(lastCode, lastContent);
	}
}
