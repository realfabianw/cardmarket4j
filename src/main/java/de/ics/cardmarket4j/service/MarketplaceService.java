package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.javatuples.Pair;

import com.google.gson.JsonElement;
import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.Game;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Product;

public class MarketplaceService extends AbstractService {

	public MarketplaceService(CardMarketService cardMarket) {
		super(cardMarket);

	}

	public void getExpansions(Game game) throws IOException {
		Pair<Integer, JsonElement> response = request("games/" + game.getId() + "/expansions", HTTPMethod.GET);
	}

	public Set<Product> getProduct(String searchQuery) throws IOException {
		Set<Product> setProducts = new HashSet<>();
		String query = "search=" + searchQuery;
		Pair<Integer, JsonElement> response = request("products/find?" + query, HTTPMethod.GET);
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("product").getAsJsonArray()) {
			setProducts.add(new Product(jEle.getAsJsonObject()));
		}
		return setProducts;
	}
}
