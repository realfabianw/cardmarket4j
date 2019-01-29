package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Article;
import de.ics.cardmarket4j.structs.ArticleFilter;
import de.ics.cardmarket4j.structs.Product;

/**
 * MarketplaceService provides a connection to several marketplace related
 * functions
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Market_Place_Information
 * @author QUE
 * @version 30.01.2019
 *
 */
public class MarketplaceService extends AbstractService {
	private static Logger LOGGER = LoggerFactory.getLogger("MarketplaceService");

	public MarketplaceService(CardMarketService cardMarket) {
		super(cardMarket);

	}

	/**
	 * Returns a List of Articles based on the given productId
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Articles
	 * @param productId
	 * @param filter
	 * @return {@code List<Article> listArticle}
	 * @throws IOException
	 */
	public List<Article> getArticles(int productId) throws IOException {
		List<Article> listArticle = new ArrayList<>();
		Pair<Integer, JsonElement> response = request("articles/" + productId, HTTPMethod.GET);
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			LOGGER.debug("JsonElement: {}", jEle);
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return null;
	}

	/**
	 * Returns a List of Articles based on the given productId and ArticleFilter
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Articles
	 * @param productId
	 * @param filter
	 * @return {@code List<Article> listArticle}
	 * @throws IOException
	 */
	public List<Article> getArticles(int productId, ArticleFilter filter) throws IOException {
		List<Article> listArticle = new ArrayList<>();
		Pair<Integer, JsonElement> response = request("articles/" + productId + filter.getQuery(), HTTPMethod.GET);
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("article").getAsJsonArray()) {
			LOGGER.debug("JsonElement: {}", jEle);
			listArticle.add(new Article(jEle.getAsJsonObject()));
		}
		return null;
	}

	/**
	 * Returns a Set of Products based on the given search query. TODO Change
	 * Parameter to ProductFilter, similar to ArticleFilter
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Find_Products
	 * @param searchQuery
	 * @return {@code Set<Product> setProduct}
	 * @throws IOException
	 */
	public Set<Product> getProduct(String searchQuery) throws IOException {
		Set<Product> setProducts = new HashSet<>();
		String query = "search=" + searchQuery;
		Pair<Integer, JsonElement> response = request("products/find?" + query, HTTPMethod.GET);
		for (JsonElement jEle : response.getValue1().getAsJsonObject().get("product").getAsJsonArray()) {
			setProducts.add(new Product(jEle.getAsJsonObject()));
		}
		return setProducts;
	}

	/**
	 * Returns a Product instance from the given productId with full details. This
	 * instance contains a PriceGuide
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Product
	 * @param productId
	 * @return {@code Product product}
	 * @throws IOException
	 */
	public Product getProductDetails(int productId) throws IOException {
		Pair<Integer, JsonElement> response = request("products/" + productId, HTTPMethod.GET);
		return new Product(response.getValue1().getAsJsonObject().get("product").getAsJsonObject());
	}
}
