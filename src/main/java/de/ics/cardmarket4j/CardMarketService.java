package de.ics.cardmarket4j;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.enums.HTTPMethod;

public interface CardMarketService {
	CardMarket getCardMarket();

	default Pair<Integer, JsonElement> request(String URL, HTTPMethod httpMethod)
			throws InvalidKeyException, MalformedURLException, NoSuchAlgorithmException, IOException {
		return getCardMarket().request(URL, httpMethod);
	}
}
