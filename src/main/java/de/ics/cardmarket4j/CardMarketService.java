package de.ics.cardmarket4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.service.AccountService;
import de.ics.cardmarket4j.service.AuthenticationService;
import de.ics.cardmarket4j.service.MarketplaceService;
import de.ics.cardmarket4j.service.OrderService;
import de.ics.cardmarket4j.service.StockService;

public class CardMarketService {
	private static final String URI = "https://api.cardmarket.com/ws/v2.0/output.json/";
	private static Logger LOGGER = LoggerFactory.getLogger("CardMarket");
	private Pair<Integer, JsonElement> lastResponse;
	private final AuthenticationService authenticationService;
	private final AccountService accountService;
	private final MarketplaceService marketplaceService;
	private final StockService stockService;
	private final OrderService orderService;

	public CardMarketService(String appToken, String appSecret, String accessToken, String accessTokenSecret) {
		this.authenticationService = new AuthenticationService(appToken, appSecret, accessToken, accessTokenSecret);
		this.accountService = new AccountService(this);
		this.marketplaceService = new MarketplaceService(this);
		this.stockService = new StockService(this);
		this.orderService = new OrderService(this);
	}

	public AccountService getAccountService() {
		return accountService;
	}

	public MarketplaceService getMarketplaceService() {
		return marketplaceService;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public StockService getStockService() {
		return stockService;
	}

	Pair<Integer, JsonElement> request(String URL, HTTPMethod httpMethod, boolean doOutput, String output)
			throws IOException {
		try {
			String uri = URI + URL.replaceAll("\\s", "%20");
			HttpURLConnection connection = (HttpURLConnection) new URL(uri).openConnection();
			String oAuthSignature = authenticationService.createOAuthSignature(uri, httpMethod);
			connection.addRequestProperty("Authorization", oAuthSignature);
			if (doOutput) {
				connection.setDoOutput(true);
			}
			connection.setRequestMethod(httpMethod.toString());
			LOGGER.debug("Request:\t{} {}", httpMethod.toString(), uri);
			// LOGGER.trace("Authorization:\t{}", oAuthSignature);
			connection.connect();
			if (doOutput) {
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(output);
				out.close();
				// LOGGER.trace("Request body:\t{}", output);
			}

			int responseCode = connection.getResponseCode();
			// LOGGER.trace("Response Code:\t{}", responseCode);
			// LOGGER.trace(HTTPUtils.getFullResponse(connection));

			BufferedReader rd = new BufferedReader(new InputStreamReader(
					(responseCode == 200 || responseCode == 206 || responseCode == 204) ? connection.getInputStream()
							: connection.getErrorStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			String responseString = sb.toString();
			// LOGGER.trace("Response Body:\t{}", responseString);

			JsonElement jResponse = new JsonParser().parse(responseString);
			LOGGER.debug("Response:\t{} {}", responseCode, jResponse);
			return new Pair<>(responseCode, jResponse);
		} catch (MalformedURLException | InvalidKeyException | NoSuchAlgorithmException e) {
			LOGGER.error("An critical error occured", e);
			System.exit(-1);
			return null;
		}
	}

//	Pair<Integer, JsonElement> request(String URL, HTTPMethod httpMethod, boolean doOutput, String output)
//			throws IOException {
//		int responseCode = 0;
//		String responseString = "";
//		try {
//			JsonElement responseContent = null;
//			// Whitespaces are not allowed in urls, so they have to be removed before trying
//			// to open a connection.
//			String fullUrl = URI + URL.replaceAll("\\s", "%20");
//			HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
//			connection.addRequestProperty("Authorization",
//					authenticationService.createOAuthSignature(fullUrl, httpMethod));
//			// I dont think these are needed here.
//			// connection.setDoInput(true);
//			if (doOutput) {
//				connection.setDoOutput(true);
//			}
//			LOGGER.trace("Request:\t{} {}", httpMethod.toString(), fullUrl);
//			connection.setRequestMethod(httpMethod.toString());
//			connection.connect();
//
//			if (doOutput) {
//				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
//				out.write(output);
//				out.close();
//			}
//
//			LOGGER.trace(HTTPUtils.getFullResponse(connection));
//
//			responseCode = connection.getResponseCode();
//
//			BufferedReader rd = new BufferedReader(new InputStreamReader(
//					responseCode == 200 ? connection.getInputStream() : connection.getErrorStream()));
//			StringBuffer sb = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				sb.append(line);
//			}
//			rd.close();
//			responseString = sb.toString();
//			responseContent = new JsonParser().parse(sb.toString());
//
//			Pair<Integer, JsonElement> response = new Pair<>(responseCode, responseContent);
//			LOGGER.trace("Response:\t{}", response);
//			lastResponse = response;
//			return response;
//		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | ProtocolException
//				| NullPointerException | JsonSyntaxException | MalformedJsonException e) {
//			LOGGER.error("Response: {}, {}", responseCode, responseString);
//			LOGGER.error("Fatal error. Exiting...", e);
//			System.exit(-1);
//			return null;
//		}
//	}
}
