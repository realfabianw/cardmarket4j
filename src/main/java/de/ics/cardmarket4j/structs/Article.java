package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;
import de.ics.cardmarket4j.enums.Game;

public class Article {
	private final int articleId;
	private final int languageId;
	private final String languageName;
	private final String comments;
	private final BigDecimal price;
	private final int quantity;
	private final boolean inShoppingCart;
	private final Product product;
	private final LocalDateTime lastEdited;
	private final String condition;
	private final boolean foil;
	private final boolean signed;
	private final boolean playset;
	private final boolean altered;

	public Article(JsonObject jObject) {
		this.articleId = JsonHelper.parseInteger(jObject, "idArticle");
		this.languageId = JsonHelper.parseInteger(jObject.get("language").getAsJsonObject(), "idLanguage");
		this.languageName = JsonHelper.parseString(jObject.get("language").getAsJsonObject(), "languageName");
		this.comments = JsonHelper.parseString(jObject, "comments");
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
		this.condition = JsonHelper.parseString(jObject, "condition");
		this.foil = JsonHelper.parseBoolean(jObject, "isFoil");
		this.signed = JsonHelper.parseBoolean(jObject, "isSigned");
		this.playset = JsonHelper.parseBoolean(jObject, "isPlayset");
		this.altered = JsonHelper.parseBoolean(jObject, "isAltered");
	}

	public int getArticleId() {
		return articleId;
	}

	public int getLanguageId() {
		return languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public String getComments() {
		return comments;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public boolean isInShoppingCart() {
		return inShoppingCart;
	}

	public Product getProduct() {
		return product;
	}

	public LocalDateTime getLastEdited() {
		return lastEdited;
	}

	public String getCondition() {
		return condition;
	}

	public boolean isFoil() {
		return foil;
	}

	public boolean isSigned() {
		return signed;
	}

	public boolean isPlayset() {
		return playset;
	}

	public boolean isAltered() {
		return altered;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", languageId=" + languageId + ", "
				+ (languageName != null ? "languageName=" + languageName + ", " : "")
				+ (comments != null ? "comments=" + comments + ", " : "")
				+ (price != null ? "price=" + price + ", " : "") + "quantity=" + quantity + ", inShoppingCart="
				+ inShoppingCart + ", " + (product != null ? "product=" + product + ", " : "")
				+ (lastEdited != null ? "lastEdited=" + lastEdited + ", " : "")
				+ (condition != null ? "condition=" + condition + ", " : "") + "foil=" + foil + ", signed=" + signed
				+ ", playset=" + playset + ", altered=" + altered + "]";
	}
}
