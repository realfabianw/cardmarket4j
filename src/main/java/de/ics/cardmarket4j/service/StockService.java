package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.CardMarketUtils;
import de.ics.cardmarket4j.enums.Game;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Article;
import de.ics.cardmarket4j.structs.IsCardMarketCard;

public class StockService extends AbstractService {
	private static Logger LOGGER = LoggerFactory.getLogger("StockService");

	public StockService(CardMarketService cardMarket) {
		super(cardMarket);
		// TODO Auto-generated constructor stub
	}

	public List<Article> decreaseArticleQuantity(IsCardMarketCard article) throws IOException {
		return decreaseListArticleQuantity(Arrays.asList(article));
	}

	public List<Article> decreaseListArticleQuantity(List<IsCardMarketCard> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (IsCardMarketCard a : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + a.getArticleId() + "</idArticle>");
			xml.append("<count>" + a.getQuantity() + "</count>");
			xml.append("</article>");
		}
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock/decrease", HTTPMethod.PUT, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			try {
				listArticle.add(new Article(jEle.getAsJsonObject()));
			} catch (Exception e) {

			}
		}
		return listArticle;
	}

	public List<Article> editArticle(IsCardMarketCard article) throws IOException {
		return editListArticles(Arrays.asList(article));
	}

	public List<Article> editListArticles(List<IsCardMarketCard> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (IsCardMarketCard a : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + a.getArticleId() + "</idArticle>");
			xml.append("<idLanguage>" + CardMarketUtils.toLanguageId(a.getLanguageCode()) + "</idLanguage>");
			xml.append("<comments>" + a.getComment() + "</comments>");
			xml.append("<count>" + a.getQuantity() + "</count>");
			xml.append("<price>" + a.getPrice() + "</price>");
			xml.append("<condition>" + a.getCondition().getId() + "</condition>");
			xml.append("<isFoil>" + a.isFoil() + "</isFoil>");
			xml.append("<isSigned>" + a.isSigned() + "</isSigned>");
			xml.append("<isAltered>" + a.isAltered() + "</isAltered>");
			xml.append("</article>");
		}
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock", HTTPMethod.PUT, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("updatedArticles").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public Article getArticleByArticleId(int articleId) throws IOException {
		Pair<Integer, JsonElement> response = request("stock/article/" + articleId, HTTPMethod.GET);
		return new Article(response.getValue1().getAsJsonObject().get("article").getAsJsonObject());
	}

	public List<Article> getListArticlesByNameAndGame(String name, Game game) throws IOException {
		Pair<Integer, JsonElement> response = request("stock/articles/" + name + "/" + game.getId(), HTTPMethod.GET);
		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public List<Article> getListArticlesInShoppingCarts() throws IOException {
		Pair<Integer, JsonElement> response = request("stock/shoppingcart-articles", HTTPMethod.GET);
		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public List<Article> getStock() throws IOException {
		List<Article> listArticle = new ArrayList<>();
		Pair<Integer, JsonElement> response = request("stock", HTTPMethod.GET);
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public List<Article> increaseArticleQuantity(IsCardMarketCard article) throws IOException {
		return increaseListArticleQuantity(Arrays.asList(article));
	}

	public List<Article> increaseListArticleQuantity(List<IsCardMarketCard> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (IsCardMarketCard a : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + a.getArticleId() + "</idArticle>");
			xml.append("<count>" + a.getQuantity() + "</count>");
			xml.append("</article>");
		}
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock/increase", HTTPMethod.PUT, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public List<Article> insertArticle(IsCardMarketCard article) throws IOException {
		return insertListArticles(Arrays.asList(article));
	}

	public List<Article> insertListArticles(List<IsCardMarketCard> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (IsCardMarketCard a : listArticles) {
			xml.append("<article>");
			xml.append("<idProduct>" + a.getProductId() + "</idProduct>");
			xml.append("<idLanguage>" + CardMarketUtils.toLanguageId(a.getLanguageCode()) + "</idLanguage>");
			xml.append("<comments>" + a.getComment() + "</comments>");
			xml.append("<count>" + a.getQuantity() + "</count>");
			xml.append("<price>" + a.getPrice() + "</price>");
			xml.append("<condition>" + a.getCondition().getId() + "</condition>");
			xml.append("<isFoil>" + a.isFoil() + "</isFoil>");
			xml.append("<isSigned>" + a.isSigned() + "</isSigned>");
			xml.append("<isAltered>" + a.isAltered() + "</isAltered>");
			xml.append("</article>");
		}
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock", HTTPMethod.POST, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("inserted").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject().get("idArticle").getAsJsonObject()));
		}
		return listArticle;
	}

	public List<Article> removeArticle(IsCardMarketCard article) throws IOException {
		return removeListArticles(Arrays.asList(article));
	}

	public List<Article> removeListArticles(List<IsCardMarketCard> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (IsCardMarketCard a : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + a.getArticleId() + "</idArticle>");
			xml.append("<count>" + a.getQuantity() + "</count>");
			xml.append("</article>");
		}
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock", HTTPMethod.DELETE, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("deleted").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}
}
