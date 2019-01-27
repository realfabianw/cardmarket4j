package de.ics.cardmarket4j;

import java.io.IOException;

import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.enums.HTTPMethod;

public abstract class AbstractService {
	final CardMarketService cardMarket;

	public AbstractService(CardMarketService cardMarket) {
		this.cardMarket = cardMarket;
	}

	protected Pair<Integer, JsonElement> request(String URL, HTTPMethod httpMethod) throws IOException {
		return cardMarket.request(URL, httpMethod, false, null);
	}

	protected Pair<Integer, JsonElement> requestWithOutput(String URL, HTTPMethod httpMethod, String output)
			throws IOException {
		return cardMarket.request(URL, httpMethod, true, output);
	}
}
