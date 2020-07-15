package de.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.JsonElement;

import de.cardmarket4j.AbstractService;
import de.cardmarket4j.CardMarketService;
import de.cardmarket4j.entity.Article;
import de.cardmarket4j.entity.Product;
import de.cardmarket4j.entity.enumeration.HTTPMethod;
import de.cardmarket4j.entity.util.ArticleFilter;
import de.cardmarket4j.entity.util.ProductFilter;
import de.cardmarket4j.util.JsonIO;

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
	 * @version 0.7
	 */
	public List<Article> getArticles(int productId) throws IOException {
		List<Article> listArticle = new ArrayList<>();
		JsonElement response = request("articles/" + productId, HTTPMethod.GET);
		for (JsonElement jEle : response.getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jEle, Article.class));
		}
		return listArticle;
	}

	/**
	 * Returns a List of Articles based on the given productId and ArticleFilter
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Articles
	 * @param productId
	 * @param filter
	 * @return {@code List<Article> listArticle}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Article> getArticles(int productId, ArticleFilter filter) throws IOException {
		List<Article> listArticle = new ArrayList<>();
		JsonElement response = request("articles/" + productId + filter.getQuery(), HTTPMethod.GET);
		for (JsonElement jEle : response.getAsJsonObject().get("article").getAsJsonArray()) {
			listArticle.add(JsonIO.getGson().fromJson(jEle, Article.class));
		}
		return listArticle;
	}

	/**
	 * Returns a Set of Products based on the given search query. TODO Change
	 * Parameter to ProductFilter, similar to ArticleFilter
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Find_Products
	 * @param searchQuery
	 * @return {@code Set<Product> setProduct}
	 * @throws IOException
	 * @version 0.7
	 */
	public Set<Product> getProduct(ProductFilter productFilter) throws IOException {
		Set<Product> setProducts = new HashSet<>();
		JsonElement response = request("products/find?" + productFilter.getQuery(), HTTPMethod.GET);
		for (JsonElement jEle : response.getAsJsonObject().get("product").getAsJsonArray()) {
			setProducts.add(JsonIO.getGson().fromJson(jEle, Product.class));
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
	 * @version 0.7
	 */
	public Product getProductDetails(int productId) throws IOException {
		JsonElement response = request("products/" + productId, HTTPMethod.GET);
		return JsonIO.getGson().fromJson(response.getAsJsonObject().get("product"), Product.class);
	}
}
