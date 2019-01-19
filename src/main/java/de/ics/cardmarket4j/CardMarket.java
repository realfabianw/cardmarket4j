package de.ics.cardmarket4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
import de.ics.cardmarket4j.service.authentication.AuthenticationService;
import de.ics.cardmarket4j.structs.Account;

public class CardMarket {
	private static final String URI = "https://api.cardmarket.com/ws/v2.0/output.json/";
	private static Logger LOGGER = LoggerFactory.getLogger("CardMarket");
	private Pair<Integer, JsonElement> lastResponse;
	private AuthenticationService authenticationService;

	public CardMarket(String appToken, String appSecret, String accessToken, String accessTokenSecret) {
		this.authenticationService = new AuthenticationService(appToken, appSecret, accessToken, accessTokenSecret);
	}

	private Pair<Integer, JsonElement> request(String URL, HTTPMethod httpMethod)
			throws MalformedURLException, IOException, InvalidKeyException, NoSuchAlgorithmException {
		int responseCode = 0;
		JsonElement responseContent = null;

		String fullUrl = URI + URL;

		HttpURLConnection connection = (HttpURLConnection) new URL(fullUrl).openConnection();
		connection.addRequestProperty("Authorization", authenticationService.createOAuthSignature(fullUrl, httpMethod));
		connection.setDoInput(true);
		connection.setDoOutput(true);
		LOGGER.trace("Request:\t{} {}", httpMethod.toString(), URI + URL);
		connection.setRequestMethod(httpMethod.toString());

		connection.connect();

		responseCode = connection.getResponseCode();

		BufferedReader rd = new BufferedReader(
				new InputStreamReader(responseCode == 200 ? connection.getInputStream() : connection.getErrorStream()));

		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		responseContent = new JsonParser().parse(sb.toString());

		Pair<Integer, JsonElement> response = new Pair<>(responseCode, responseContent);
		LOGGER.trace("Response:\t{}", response);
		lastResponse = response;
		return response;
	}

	public Account getAccountInformation() {
		try {
			return new Account(request("account", HTTPMethod.GET).getValue1());
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			return null;
		}
	}

	public boolean setVacationStatus(boolean vacation) {
		String vacationParameter = vacation == true ? "true" : "false";
		try {
			Pair<Integer, JsonElement> result = request("account/vacation?onVacation=" + vacationParameter,
					HTTPMethod.PUT);
			return true;
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

}
