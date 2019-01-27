package de.ics.cardmarket4j.structs;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;

public class Product {
	// Essential Data for Identification & Display
	private final int productId;
	private String englishName;
	private String expansion;

	// Aditional Data - Marketplace Information
	private int availableArticles;
	private int availableFoils;
	private PriceGuide priceGuide;

	public Product(int productId) {
		this.productId = productId;
	}

	public Product(int productId, String englishName, String expansion, int availableArticles, int availableFoils,
			PriceGuide priceGuide) {
		this.productId = productId;
		this.englishName = englishName;
		this.expansion = expansion;
		this.availableArticles = availableArticles;
		this.availableFoils = availableFoils;
		this.priceGuide = priceGuide;
	}

	public Product(int productId, JsonObject jProductSearch) {
		this.productId = productId;
		this.englishName = JsonHelper.parseString(jProductSearch, "enName");
		this.expansion = JsonHelper.parseString(jProductSearch, "expansionName");
		this.availableArticles = JsonHelper.parseInteger(jProductSearch, "countArticles");
		this.availableFoils = JsonHelper.parseInteger(jProductSearch, "countFoils");
		try {
			JsonObject priceGuide = jProductSearch.get("priceGuide").getAsJsonObject();
			this.priceGuide = new PriceGuide(JsonHelper.parseBigDecimal(priceGuide, "SELL"),
					JsonHelper.parseBigDecimal(priceGuide, "LOW"), JsonHelper.parseBigDecimal(priceGuide, "LOWEX"),
					JsonHelper.parseBigDecimal(priceGuide, "LOWFOIL"), JsonHelper.parseBigDecimal(priceGuide, "AVG"),
					JsonHelper.parseBigDecimal(priceGuide, "TREND"));
		} catch (Exception e) {
			this.priceGuide = null;
		}
	}
	
	public Product(JsonObject jProductSearch) {
		this.productId = JsonHelper.parseInteger(jProductSearch, "idProduct");
		this.englishName = JsonHelper.parseString(jProductSearch, "enName");
		this.expansion = JsonHelper.parseString(jProductSearch, "expansionName");
		this.availableArticles = JsonHelper.parseInteger(jProductSearch, "countArticles");
		this.availableFoils = JsonHelper.parseInteger(jProductSearch, "countFoils");
		try {
			JsonObject priceGuide = jProductSearch.get("priceGuide").getAsJsonObject();
			this.priceGuide = new PriceGuide(JsonHelper.parseBigDecimal(priceGuide, "SELL"),
					JsonHelper.parseBigDecimal(priceGuide, "LOW"), JsonHelper.parseBigDecimal(priceGuide, "LOWEX"),
					JsonHelper.parseBigDecimal(priceGuide, "LOWFOIL"), JsonHelper.parseBigDecimal(priceGuide, "AVG"),
					JsonHelper.parseBigDecimal(priceGuide, "TREND"));
		} catch (Exception e) {
			this.priceGuide = null;
		}
	}

	public int getAvailableArticles() {
		return availableArticles;
	}

	public int getAvailableFoils() {
		return availableFoils;
	}

	public String getEnglishName() {
		return englishName;
	}

	public String getExpansion() {
		return expansion;
	}

	public PriceGuide getPriceGuide() {
		return priceGuide;
	}

	public int getProductId() {
		return productId;
	}

	public void setAvailableArticles(int availableArticles) {
		this.availableArticles = availableArticles;
	}

	public void setAvailableFoils(int availableFoils) {
		this.availableFoils = availableFoils;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public void setExpansion(String expansion) {
		this.expansion = expansion;
	}

	public void setPriceGuide(PriceGuide priceGuide) {
		this.priceGuide = priceGuide;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", "
				+ (englishName != null ? "englishName=" + englishName + ", " : "")
				+ (expansion != null ? "expansion=" + expansion + ", " : "") + "availableArticles=" + availableArticles
				+ ", availableFoils=" + availableFoils + ", " + (priceGuide != null ? "priceGuide=" + priceGuide : "")
				+ "]";
	}
}
