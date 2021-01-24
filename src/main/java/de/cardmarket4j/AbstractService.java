package de.cardmarket4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;

import de.cardmarket4j.entity.enumeration.HTTPMethod;
import de.cardmarket4j.util.HTTPResponse;

public abstract class AbstractService {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getSimpleName());
	final CardMarketService cardMarket;

	public AbstractService(CardMarketService cardMarket) {
		this.cardMarket = cardMarket;
	}

	protected HTTPResponse getLastResponse() {
		return cardMarket.getLastResponse();
	}

	protected JsonElement request(String URL, HTTPMethod httpMethod) throws IOException {
		return cardMarket.request(URL, httpMethod).getResponseJsonElement();
	}

	protected JsonElement request(String URL, HTTPMethod httpMethod, String requestBody) throws IOException {
		return cardMarket.request(URL, httpMethod, requestBody).getResponseJsonElement();
	}
}
