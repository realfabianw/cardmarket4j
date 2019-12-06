package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.entity.Article;
import de.ics.cardmarket4j.entity.CardMarketArticle;
import de.ics.cardmarket4j.entity.enumeration.Game;
import de.ics.cardmarket4j.entity.enumeration.HTTPMethod;
import de.ics.cardmarket4j.util.CardMarketUtils;
import de.ics.cardmarket4j.util.JsonIO;

public class StockService extends AbstractService {
	public StockService(CardMarketService cardMarket) {
		super(cardMarket);
	}

	/**
	 * Decreases the quantity of an article in the users stock.
	 * 
	 * @see https://api.cardmarket.com/ws/documentation/API_2.0:Stock_Quantity
	 * @param listArticles
	 * @return {@code List<Article> listArticles}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Article> decreaseArticleQuantity(CardMarketArticle article) throws IOException {
		return decreaseListArticleQuantity(Arrays.asList(article));
	}

	/**
	 * Decreases the quantity of a given list of articles in the users stock.
	 * 
	 * @see https://api.cardmarket.com/ws/documentation/API_2.0:Stock_Quantity
	 * @param listArticles
	 * @return {@code List<Article> listArticles}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Article> decreaseListArticleQuantity(List<CardMarketArticle> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (CardMarketArticle article : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + article.getArticleId() + "</idArticle>");
			xml.append("<count>" + article.getQuantity() + "</count>");
			xml.append("</article>");
		}
		xml.append("</request>");
		JsonElement response = request("stock/decrease", HTTPMethod.PUT, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("article").getAsJsonArray()) {
			try {
				listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
			} catch (Exception e) {

			}
		}
		return listArticle;
	}

	public List<Article> editArticle(CardMarketArticle article) throws IOException {
		return editListArticles(Arrays.asList(article));
	}

	public List<Article> editListArticles(List<CardMarketArticle> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (CardMarketArticle article : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + article.getArticleId() + "</idArticle>");
			xml.append("<idLanguage>" + CardMarketUtils.toLanguageId(article.getLanguageCode()) + "</idLanguage>");
			xml.append("<comments>" + article.getComment() + "</comments>");
			xml.append("<count>" + article.getQuantity() + "</count>");
			xml.append("<price>" + article.getPrice() + "</price>");
			xml.append("<condition>" + article.getCondition().getId() + "</condition>");
			xml.append("<isFoil>" + article.isFoil() + "</isFoil>");
			xml.append("<isSigned>" + article.isSigned() + "</isSigned>");
			xml.append("<isAltered>" + article.isAltered() + "</isAltered>");
			xml.append("</article>");
		}
		xml.append("</request>");
		JsonElement response = request("stock", HTTPMethod.PUT, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("updatedArticles").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
		}
		return listArticle;
	}

	public Article getArticleByArticleId(int articleId) throws IOException {
		JsonElement response = request("stock/article/" + articleId, HTTPMethod.GET);
		return JsonIO.getGson().fromJson(response.getAsJsonObject().get("article"), Article.class);
	}

	public List<Article> getListArticlesByNameAndGame(String name, Game game) throws IOException {
		JsonElement response = request("stock/articles/" + name + "/" + game.getId(), HTTPMethod.GET);
		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
		}
		return listArticle;
	}

	public List<Article> getListArticlesInShoppingCarts() throws IOException {
		JsonElement response = request("stock/shoppingcart-articles", HTTPMethod.GET);
		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
		}
		return listArticle;
	}

	/**
	 * Returns the users stock. TODO implement paging for more than 1000 results
	 * 
	 * @return {@code List<Article> listArticles}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Article> getStock() throws IOException {
		List<Article> listArticle = new ArrayList<>();
		JsonElement response = request("stock", HTTPMethod.GET);
		for (JsonElement jElement : response.getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
		}
		return listArticle;
	}

	/**
	 * Increases the quantity of an article in the users stock.
	 * 
	 * @see https://api.cardmarket.com/ws/documentation/API_2.0:Stock_Quantity
	 * @param listArticles
	 * @return {@code List<Article> listArticles}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Article> increaseArticleQuantity(CardMarketArticle article) throws IOException {
		return increaseListArticleQuantity(Arrays.asList(article));
	}

	/**
	 * Increases the quantity of a given list of articles in the users stock.
	 * 
	 * @see https://api.cardmarket.com/ws/documentation/API_2.0:Stock_Quantity
	 * @param listArticles
	 * @return {@code List<Article> listArticles}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Article> increaseListArticleQuantity(List<CardMarketArticle> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (CardMarketArticle article : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + article.getArticleId() + "</idArticle>");
			xml.append("<count>" + article.getQuantity() + "</count>");
			xml.append("</article>");
		}
		xml.append("</request>");
		JsonElement response = request("stock/increase", HTTPMethod.PUT, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
		}
		return listArticle;
	}

	public List<Article> insertArticle(CardMarketArticle article) throws IOException {
		return insertListArticles(Arrays.asList(article));
	}

	public List<Article> insertListArticles(List<CardMarketArticle> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (CardMarketArticle article : listArticles) {
			xml.append("<article>");
			xml.append("<idProduct>" + article.getProductId() + "</idProduct>");
			xml.append("<idLanguage>" + CardMarketUtils.toLanguageId(article.getLanguageCode()) + "</idLanguage>");
			xml.append("<comments>" + article.getComment() + "</comments>");
			xml.append("<count>" + article.getQuantity() + "</count>");
			xml.append("<price>" + article.getPrice() + "</price>");
			xml.append("<condition>" + article.getCondition().getId() + "</condition>");
			xml.append("<isFoil>" + article.isFoil() + "</isFoil>");
			xml.append("<isSigned>" + article.isSigned() + "</isSigned>");
			xml.append("<isAltered>" + article.isAltered() + "</isAltered>");
			xml.append("</article>");
		}
		xml.append("</request>");
		JsonElement response = request("stock", HTTPMethod.POST, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("inserted").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement.getAsJsonObject().get("idArticle"), Article.class));
		}
		return listArticle;
	}

	public List<Article> removeArticle(CardMarketArticle article) throws IOException {
		return removeListArticles(Arrays.asList(article));
	}

	public List<Article> removeListArticles(List<CardMarketArticle> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (CardMarketArticle article : listArticles) {
			xml.append("<article>");
			xml.append("<idArticle>" + article.getArticleId() + "</idArticle>");
			xml.append("<count>" + article.getQuantity() + "</count>");
			xml.append("</article>");
		}
		xml.append("</request>");
		JsonElement response = request("stock", HTTPMethod.DELETE, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jElement : response.getAsJsonObject().get("deleted").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jElement, Article.class));
		}
		return listArticle;
	}
}
