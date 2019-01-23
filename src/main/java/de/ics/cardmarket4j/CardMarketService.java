package de.ics.cardmarket4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.service.AccountService;
import de.ics.cardmarket4j.service.AuthenticationService;
import de.ics.cardmarket4j.service.MarketplaceService;
import de.ics.cardmarket4j.service.StockService;

public class CardMarketService {
	private static final String URI = "https://api.cardmarket.com/ws/v2.0/output.json/";
	private static Logger LOGGER = LoggerFactory.getLogger("CardMarket");
	private Pair<Integer, JsonElement> lastResponse;
	private final AuthenticationService authenticationService;
	private final AccountService accountService;
	private final MarketplaceService marketplaceService;
	private final StockService stockService;

	public CardMarketService(String appToken, String appSecret, String accessToken, String accessTokenSecret) {
		this.authenticationService = new AuthenticationService(appToken, appSecret, accessToken, accessTokenSecret);
		this.accountService = new AccountService(this);
		this.marketplaceService = new MarketplaceService(this);
		this.stockService = new StockService(this);
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public MarketplaceService getMarketplaceService() {
		return marketplaceService;
	}

	Pair<Integer, JsonElement> request(String URL, HTTPMethod httpMethod) throws IOException {
		int responseCode = 0;
		String responseString = "";
		try {
			JsonElement responseContent = null;
			// Whitespaces are not allowed in urls, so they have to be removed before trying
			// to open a connection.
			String fullUrl = URI + URL.replaceAll("\\s", "%20");
			HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
			connection.addRequestProperty("Authorization",
					authenticationService.createOAuthSignature(fullUrl, httpMethod));
			//I dont think these are needed here.
			//connection.setDoInput(true);
			//connection.setDoOutput(true);
			LOGGER.trace("Request:\t{} {}", httpMethod.toString(), fullUrl);
			connection.setRequestMethod(httpMethod.toString());
			connection.connect();
			responseCode = connection.getResponseCode();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					responseCode == 200 ? connection.getInputStream() : connection.getErrorStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			responseString = sb.toString();
			responseContent = new JsonParser().parse(sb.toString());

			Pair<Integer, JsonElement> response = new Pair<>(responseCode, responseContent);
			LOGGER.trace("Response:\t{}", response);
			lastResponse = response;
			return response;
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | ProtocolException
				| NullPointerException | JsonSyntaxException | MalformedJsonException e) {
			LOGGER.error("Response: {}, {}", responseCode, responseString);
			LOGGER.error("Fatal error. Exiting...", e);
			System.exit(-1);
			return null;
		}
	}
	
	Pair<Integer, JsonElement> requestWithOutput(String URL, HTTPMethod httpMethod, String output) throws IOException {
		int responseCode = 0;
		String responseString = "";
		try {
			JsonElement responseContent = null;
			// Whitespaces are not allowed in urls, so they have to be removed before trying
			// to open a connection.
			String fullUrl = URI + URL.replaceAll("\\s", "%20");
			HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
			connection.addRequestProperty("Authorization",
					authenticationService.createOAuthSignature(fullUrl, httpMethod));
			connection.setDoOutput(true);
			LOGGER.trace("Request:\t{} {}", httpMethod.toString(), fullUrl);
			connection.setRequestMethod(httpMethod.toString());
			connection.connect();
			
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(output);
			out.close();
			responseCode = connection.getResponseCode();

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					responseCode == 200 ? connection.getInputStream() : connection.getErrorStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			responseString = sb.toString();
			responseContent = new JsonParser().parse(sb.toString());

			Pair<Integer, JsonElement> response = new Pair<>(responseCode, responseContent);
			LOGGER.trace("Response:\t{}", response);
			lastResponse = response;
			return response;
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | ProtocolException
				| NullPointerException | JsonSyntaxException | MalformedJsonException e) {
			LOGGER.error("Response: {}, {}", responseCode, responseString);
			LOGGER.error("Fatal error. Exiting...", e);
			System.exit(-1);
			return null;
		}
	}

	public StockService getStockService() {
		return stockService;
	}
}
