package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarket;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Article;

public class StockService extends AbstractService {

	public StockService(CardMarket cardMarket) {
		super(cardMarket);
		// TODO Auto-generated constructor stub
	}

	public List<Article> getStock() throws IOException {
		List<Article> listArticle = new ArrayList<>();
		Pair<Integer, JsonElement> response = request("stock", HTTPMethod.GET);
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public void insertArticle() {
		// TODO zum inserieren muss die ProductId angegeben werden. Diese muss vorher
		// irgendwie herausgefunden werden
	}

}
