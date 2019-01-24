package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;
import de.ics.cardmarket4j.enums.Condition;
import de.ics.cardmarket4j.enums.Game;
import de.ics.cardmarket4j.enums.Language;

public class Article {
	// Essential Data for Article Identification
	private final int articleId;

	// Essential Data to add an Article/Product to the stock
	private final Product product;
	private final Language language;
	private final int quantity;
	private final BigDecimal price;
	private final Condition condition;
	private final String comment;
	private final boolean foil;
	private final boolean signed;
	private final boolean altered;
	private final boolean playset;

	// Additional Data
	private boolean inShoppingCart;
	private LocalDateTime lastEdited;

	/**
	 * Constructor to add an article to the stock.
	 * 
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
	public Article(int productId, Language language, int quantity, BigDecimal price, Condition condition,
			String comment, boolean foil, boolean signed, boolean altered, boolean playset) {
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
		jObject = jObject.get("idArticle").getAsJsonObject();

		this.articleId = JsonHelper.parseInteger(jObject, "idArticle");

		this.language = Language
				.parseId(JsonHelper.parseInteger(jObject.get("language").getAsJsonObject(), "idLanguage"));

		this.comment = JsonHelper.parseString(jObject, "comments");
		this.price = JsonHelper.parseBigDecimal(jObject, "price");
		this.quantity = JsonHelper.parseInteger(jObject, "count");
		this.inShoppingCart = JsonHelper.parseBoolean(jObject, "inShoppingCart");

		this.product = new Product(JsonHelper.parseInteger(jObject, "idProduct"),
				Game.parseId(JsonHelper.parseInteger(jObject.get("product").getAsJsonObject(), "idGame")),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "enName"),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "locName"),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "image"),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "expansion"),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "nr"),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "expIcon"),
				JsonHelper.parseString(jObject.get("product").getAsJsonObject(), "rarity"));

		this.lastEdited = JsonHelper.parseLocalDateTime(jObject, "lastEdited", DateTimeFormatter.ISO_DATE_TIME);

		this.condition = Condition.parseId(JsonHelper.parseString(jObject, "condition"));
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

	public void setInShoppingCart(boolean inShoppingCart) {
		this.inShoppingCart = inShoppingCart;
	}

	public void setLastEdited(LocalDateTime lastEdited) {
		this.lastEdited = lastEdited;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", " + (product != null ? "product=" + product + ", " : "")
				+ (language != null ? "language=" + language + ", " : "") + "quantity=" + quantity + ", "
				+ (price != null ? "price=" + price + ", " : "")
				+ (condition != null ? "condition=" + condition + ", " : "")
				+ (comment != null ? "comment=" + comment + ", " : "") + "foil=" + foil + ", signed=" + signed
				+ ", altered=" + altered + ", playset=" + playset + ", inShoppingCart=" + inShoppingCart + ", "
				+ (lastEdited != null ? "lastEdited=" + lastEdited : "") + "]";
	}
}
