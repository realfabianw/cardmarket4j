package de.ics.cardmarket4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.enums.HTTPMethod;

public abstract class AbstractService {
	protected final Logger LOGGER = LoggerFactory.getLogger(getClass().getSimpleName());
	final CardMarketService cardMarket;

	public AbstractService(CardMarketService cardMarket) {
		this.cardMarket = cardMarket;
	}

	protected JsonElement request(String URL, HTTPMethod httpMethod) throws IOException {
		return cardMarket.request(URL, httpMethod).getValue1();
	}

	protected JsonElement request(String URL, HTTPMethod httpMethod, String requestBody) throws IOException {
		return cardMarket.request(URL, httpMethod, requestBody).getValue1();
	}
}
