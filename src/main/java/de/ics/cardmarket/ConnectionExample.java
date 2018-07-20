package de.ics.cardmarket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.GregorianCalendar;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class ConnectionExample {

	private String appToken;
	private String appSecret;
	private String accessToken;
	private String accessTokenSecret;

	private Throwable lastError;
	private int lastCode;
	private String lastContent;
	private boolean debug;

	/**
	 * Constructor. Fill parameters according to given MKM profile app parameters.
	 * 
	 * @param appToken
	 * @param appSecret
	 * @param accessToken
	 * @param accessSecret
	 */
	public ConnectionExample(String appToken, String appSecret, String accessToken, String accessSecret) {
		this.appToken = appToken;
		this.appSecret = appSecret;
		this.accessToken = accessToken;
		this.accessTokenSecret = accessSecret;

		this.lastError = null;
		this.debug = true;
	}

	/**
	 * Activates the console debug messages
	 * 
	 * @param flag true if you want to enable console messages; false to disable any
	 *             notification.
	 */
	public void setDebug(boolean flag) {
		debug = flag;
	}

	/**
	 * Encoding function. To avoid deprecated version, the encoding used is UTF-8.
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private String rawurlencode(String str) throws UnsupportedEncodingException {
		return URLEncoder.encode(str, "UTF-8");
	}

	private void _debug(String msg) {
		if (debug) {
			System.out.print(GregorianCalendar.getInstance().getTime());
			System.out.print(" > ");
			System.out.println(msg);
		}
	}

	/**
	 * Get last Error exception.
	 * 
	 * @return null if no errors; instead the raised exception.
	 */
	public Throwable lastError() {
		return lastError;
	}

	/**
	 * Perform the request to given url with OAuth 1.0a API.
	 * 
	 * @param requestURL url to be requested. Ex.
	 *                   https://www.mkmapi.eu/ws/v1.1/products/island/1/1/false
	 * @return true if request was successfully executed. You can retrieve the
	 *         content with responseContent();
	 */
	public boolean request(String requestURL) {
		lastError = null;
		lastCode = 0;
		lastContent = "";
		try {

			_debug("Requesting " + requestURL);

			String realm = requestURL;
			String oauth_version = "1.0";
			String oauth_consumer_key = appToken;
			String oauth_token = accessToken;
			String oauth_signature_method = "HMAC-SHA1";
			// String oauth_timestamp = "" + (System.currentTimeMillis()/1000) ;
			String oauth_timestamp = "1407917892";
			// String oauth_nonce = "" + System.currentTimeMillis() ;
			String oauth_nonce = "53eb1f44909d6";

			String encodedRequestURL = rawurlencode(requestURL);

			String baseString = "GET&" + encodedRequestURL + "&";

			String paramString = "oauth_consumer_key=" + rawurlencode(oauth_consumer_key) + "&" + "oauth_nonce="
					+ rawurlencode(oauth_nonce) + "&" + "oauth_signature_method=" + rawurlencode(oauth_signature_method)
					+ "&" + "oauth_timestamp=" + rawurlencode(oauth_timestamp) + "&" + "oauth_token="
					+ rawurlencode(oauth_token) + "&" + "oauth_version=" + rawurlencode(oauth_version);

			baseString = baseString + rawurlencode(paramString);

			String signingKey = rawurlencode(appSecret) + "&" + rawurlencode(accessTokenSecret);

			Mac mac = Mac.getInstance("HmacSHA1");
			SecretKeySpec secret = new SecretKeySpec(signingKey.getBytes(), mac.getAlgorithm());
			mac.init(secret);
			byte[] digest = mac.doFinal(baseString.getBytes());
			String oauth_signature = DatatypeConverter.printBase64Binary(digest); // Base64.encode(digest) ;

			String authorizationProperty = "OAuth " + "realm=\"" + realm + "\", " + "oauth_version=\"" + oauth_version
					+ "\", " + "oauth_timestamp=\"" + oauth_timestamp + "\", " + "oauth_nonce=\"" + oauth_nonce + "\", "
					+ "oauth_consumer_key=\"" + oauth_consumer_key + "\", " + "oauth_token=\"" + oauth_token + "\", "
					+ "oauth_signature_method=\"" + oauth_signature_method + "\", " + "oauth_signature=\""
					+ oauth_signature + "\"";

			HttpURLConnection connection = (HttpURLConnection) new URL(requestURL).openConnection();
			connection.addRequestProperty("Authorization", authorizationProperty);
			connection.connect();

			// from here standard actions...
			// read response code... read input stream.... close connection...

			lastCode = connection.getResponseCode();

			_debug("Response Code is " + lastCode);

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

			return (lastCode == 200);

		} catch (Exception e) {
			_debug("(!) Error while requesting " + requestURL);
			lastError = e;
		}
		return false;
	}

	/**
	 * Get response code from last request.
	 * 
	 * @return
	 */
	public int responseCode() {
		return lastCode;
	}

	/**
	 * Get response content from last request.
	 * 
	 * @return
	 */
	public String responseContent() {
		return lastContent;
	}
}
