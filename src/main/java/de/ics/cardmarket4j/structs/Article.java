package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;
import de.ics.cardmarket4j.enums.Condition;
import de.ics.cardmarket4j.enums.Language;

public class Article {
	// Essential Data for Article Identification
	private final int articleId;

	// Essential Data to add an Article/Product to the stock
	private Product product;
	private Language language;
	private int quantity;
	private BigDecimal price;
	private Condition condition;
	private String comment;
	private boolean foil;
	private boolean signed;
	private boolean altered;
	private boolean playset;

	// Additional Data
	private User seller;
	private boolean inShoppingCart;
	private LocalDateTime lastEdited;

	/**
	 * This constructor is needed, when you want to add an article to the cardmarket-store.
	 * @param productId
	 * @param language
	 * @param quantity
	 * @param price
	 * @param condition
	 * @param comment
	 * @param foil
	 * @param signed
	 * @param altered
	 * @param playset
	 */
	public Article(int articleId, int productId, Language language, int quantity, BigDecimal price,
			Condition condition, String comment, boolean foil, boolean signed, boolean altered, boolean playset) {
		this.articleId = articleId;
		this.product = new Product(productId);
		this.language = language;
		this.quantity = quantity;
		this.price = price;
		this.condition = condition;
		this.comment = comment;
		this.foil = foil;
		this.signed = signed;
		this.altered = altered;
		this.playset = playset;
	}

	/**
	 * This constructor is needed, when you want to add an article to the cardmarket-store.
	 * @param productId
	 * @param language
	 * @param quantity
	 * @param price
	 * @param condition
	 * @param comment
	 * @param foil
	 * @param signed
	 * @param altered
	 * @param playset
	 */
	public Article(int productId, Language language, int quantity, BigDecimal price,
			Condition condition, String comment, boolean foil, boolean signed, boolean altered, boolean playset) {
		this.articleId = 0;
		this.product = new Product(productId);
		this.language = language;
		this.quantity = quantity;
		this.price = price;
		this.condition = condition;
		this.comment = comment;
		this.foil = foil;
		this.signed = signed;
		this.altered = altered;
		this.playset = playset;
	}

	public Article(JsonObject jObject) {
		this.articleId = JsonHelper.parseInteger(jObject, "idArticle");

		try {
			this.language = Language
					.parseId(JsonHelper.parseInteger(jObject.get("language").getAsJsonObject(), "idLanguage"));
		} catch (NullPointerException e) {
			this.language = null;
		}

		this.comment = JsonHelper.parseString(jObject, "comments");
		this.price = JsonHelper.parseBigDecimal(jObject, "price");
		this.quantity = JsonHelper.parseInteger(jObject, "count");
		this.inShoppingCart = JsonHelper.parseBoolean(jObject, "inShoppingCart");

		try {
			this.product = new Product(JsonHelper.parseInteger(jObject, "idProduct"),
					jObject.get("product").getAsJsonObject());
		} catch (NullPointerException e) {
			this.product = new Product(JsonHelper.parseInteger(jObject, "idProduct"));
		}
		try {
			this.seller = new User(jObject.get("seller").getAsJsonObject());
		} catch (NullPointerException e) {

		}
		this.lastEdited = JsonHelper.parseLocalDateTime(jObject, "lastEdited", DateTimeFormatter.ISO_DATE_TIME);
		try {
			this.condition = Condition.parseId(JsonHelper.parseString(jObject, "condition"));
		} catch (IllegalArgumentException e) {
			this.condition = null;
		}
		this.foil = JsonHelper.parseBoolean(jObject, "isFoil");
		this.signed = JsonHelper.parseBoolean(jObject, "isSigned");
		this.playset = JsonHelper.parseBoolean(jObject, "isPlayset");
		this.altered = JsonHelper.parseBoolean(jObject, "isAltered");
	}

	public int getArticleId() {
		return articleId;
	}

	public String getComment() {
		return comment;
	}

	public Condition getCondition() {
		return condition;
	}

	public Language getLanguage() {
		return language;
	}

	public LocalDateTime getLastEdited() {
		return lastEdited;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public User getSeller() {
		return seller;
	}

	public boolean isAltered() {
		return altered;
	}

	public boolean isFoil() {
		return foil;
	}

	public boolean isInShoppingCart() {
		return inShoppingCart;
	}

	public boolean isPlayset() {
		return playset;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setAltered(boolean altered) {
		this.altered = altered;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public void setFoil(boolean foil) {
		this.foil = foil;
	}

	public void setInShoppingCart(boolean inShoppingCart) {
		this.inShoppingCart = inShoppingCart;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void setLastEdited(LocalDateTime lastEdited) {
		this.lastEdited = lastEdited;
	}

	public void setPlayset(boolean playset) {
		this.playset = playset;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}
	
	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", " + (product != null ? "product=" + product + ", " : "")
				+ (language != null ? "language=" + language + ", " : "") + "quantity=" + quantity + ", "
				+ (price != null ? "price=" + price + ", " : "")
				+ (condition != null ? "condition=" + condition + ", " : "")
				+ (comment != null ? "comment=" + comment + ", " : "") + "foil=" + foil + ", signed=" + signed
				+ ", altered=" + altered + ", playset=" + playset + ", "
				+ (seller != null ? "seller=" + seller + ", " : "") + "inShoppingCart=" + inShoppingCart + ", "
				+ (lastEdited != null ? "lastEdited=" + lastEdited : "") + "]";
	}
}
