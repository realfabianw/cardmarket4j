package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Article;

public class StockService extends AbstractService {
	private static Logger LOGGER = LoggerFactory.getLogger("StockService");

	public StockService(CardMarketService cardMarket) {
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
	
	/**
	 * @deprecated
	 * @param productId
	 * @param languageId
	 * @param quantity
	 * @param price
	 * @param condition
	 * @param comment
	 * @param isFoil
	 * @param isSigned
	 * @param isAltered
	 * @param isPlayset
	 * @return
	 * @throws IOException
	 */
	private List<Article> insertArticle(int productId, int languageId, int quantity, BigDecimal price, String condition,
			String comment, boolean isFoil, boolean isSigned, boolean isAltered, boolean isPlayset) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		xml.append("<article>");
		xml.append("<idProduct>" + productId + "</idProduct>");
		xml.append("<idLanguage>" + languageId + "</idLanguage>");
		xml.append("<comments>" + comment + "</comments>");
		xml.append("<count>" + quantity + "</count>");
		xml.append("<price>" + price + "</price>");
		xml.append("<condition>" + condition + "</condition>");
		xml.append("<isFoil>" + isFoil + "</isFoil>");
		xml.append("<isSigned>" + isSigned + "</isSigned>");
		xml.append("<isAltered>" + isAltered + "</isAltered>");
		xml.append("<isPlayset>" + isPlayset + "</isPlayset>");
		xml.append("</article>");
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock", HTTPMethod.POST, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

	public List<Article> insertArticle(Article article) throws IOException {
		List<Article> listArticles = new ArrayList<>();
		listArticles.add(article);
		return insertListArticles(listArticles);
	}

	public List<Article> insertListArticles(List<Article> listArticles) throws IOException {
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
		xml.append("<request>");
		for (Article a : listArticles) {
			xml.append("<article>");
			xml.append("<idProduct>" + a.getProduct().getProductId() + "</idProduct>");
			xml.append("<idLanguage>" + a.getLanguage().getId() + "</idLanguage>");
			xml.append("<comments>" + a.getComment() + "</comments>");
			xml.append("<count>" + a.getQuantity() + "</count>");
			xml.append("<price>" + a.getPrice() + "</price>");
			xml.append("<condition>" + a.getCondition().getId() + "</condition>");
			xml.append("<isFoil>" + a.isFoil() + "</isFoil>");
			xml.append("<isSigned>" + a.isSigned() + "</isSigned>");
			xml.append("<isAltered>" + a.isAltered() + "</isAltered>");
			xml.append("<isPlayset>" + a.isPlayset() + "</isPlayset>");
			xml.append("</article>");
		}
		xml.append("</request>");
		LOGGER.trace("XML: {}", xml);
		Pair<Integer, JsonElement> response = requestWithOutput("stock", HTTPMethod.POST, xml.toString());

		List<Article> listArticle = new ArrayList<>();
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("inserted").getAsJsonArray()) {
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return listArticle;
	}

}
