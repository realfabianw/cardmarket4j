package de.ics.cardmarket4j.structs;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;
import de.ics.cardmarket4j.enums.Game;

public class Product {
	// Essential Data for Identification & Display
	private final int productId;
	private String englishName;
	private String expansion;

	// Aditional Data - Marketplace Information
	private int availableArticles;
	private int availableFoils;

	// Additional Data - Card Information - TODO do i need those?
	private int metaProductId;
	private int amountOfReprints;
	private Game game;
	private String localizedName;
	private String imageUrl;
	private String nr;
	private String expIcon;
	private String rarity;

	public Product(int productId) {
		this.productId = productId;
	}
	
	public Product(int productId, Game game, String englishName, String localizedName, String imageUrl,
			String expansion, String nr, String expIcon, String rarity) {
		this.productId = productId;
		this.game = game;
		this.englishName = englishName;
		this.localizedName = localizedName;
		this.imageUrl = imageUrl;
		this.expansion = expansion;
		this.nr = nr;
		this.expIcon = expIcon;
		this.rarity = rarity;
	}

	/**
	 * { "idProduct": 17111, "idMetaproduct": 3291, "countReprints": 27, "enName":
	 * "Lightning Bolt", "locName": "Lightning Bolt", "localization": [ { "name":
	 * "Lightning Bolt", "idLanguage": "1", "languageName": "English" }, { "name":
	 * "Foudre", "idLanguage": "2", "languageName": "French" }, { "name":
	 * "Blitzschlag", "idLanguage": "3", "languageName": "German" }, { "name":
	 * "Relámpago", "idLanguage": "4", "languageName": "Spanish" }, { "name":
	 * "Fulmine", "idLanguage": "5", "languageName": "Italian" } ], "website":
	 * "/en/Magic/Products/Singles/Collectors-Edition/Lightning-Bolt", "image":
	 * "./img/items/1/CED/17111.jpg", "gameName": "Magic the Gathering",
	 * "categoryName": "Magic Single", "idGame": "1", "number": null, "rarity":
	 * "Common", "expansionName": "Collectors' Edition", "expansionIcon": 59,
	 * "countArticles": 18, "countFoils": 0, "links": [ { "rel": "product", "href":
	 * "/products/17111", "method": "GET" } ] }
	 * 
	 * @param jProductSearch
	 */
	public Product(JsonObject jProductSearch) {
		this.productId = JsonHelper.parseInteger(jProductSearch, "idProduct");
		this.englishName = JsonHelper.parseString(jProductSearch, "enName");
		this.expansion = JsonHelper.parseString(jProductSearch, "expansionName");
		this.availableArticles = JsonHelper.parseInteger(jProductSearch, "countArticles");
		this.availableFoils = JsonHelper.parseInteger(jProductSearch, "countFoils");
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", "
				+ (englishName != null ? "englishName=" + englishName + ", " : "")
				+ (expansion != null ? "expansion=" + expansion + ", " : "") + "availableArticles=" + availableArticles
				+ ", availableFoils=" + availableFoils + ", metaProductId=" + metaProductId + ", amountOfReprints="
				+ amountOfReprints + ", " + (game != null ? "game=" + game + ", " : "")
				+ (localizedName != null ? "localizedName=" + localizedName + ", " : "")
				+ (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") + (nr != null ? "nr=" + nr + ", " : "")
				+ (expIcon != null ? "expIcon=" + expIcon + ", " : "") + (rarity != null ? "rarity=" + rarity : "")
				+ "]";
	}

	public int getAvailableArticles() {
		return availableArticles;
	}

	public void setAvailableArticles(int availableArticles) {
		this.availableArticles = availableArticles;
	}

	public int getAvailableFoils() {
		return availableFoils;
	}

	public void setAvailableFoils(int availableFoils) {
		this.availableFoils = availableFoils;
	}

	public int getMetaProductId() {
		return metaProductId;
	}

	public void setMetaProductId(int metaProductId) {
		this.metaProductId = metaProductId;
	}

	public int getAmountOfReprints() {
		return amountOfReprints;
	}

	public void setAmountOfReprints(int amountOfReprints) {
		this.amountOfReprints = amountOfReprints;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getLocalizedName() {
		return localizedName;
	}

	public void setLocalizedName(String localizedName) {
		this.localizedName = localizedName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public String getExpIcon() {
		return expIcon;
	}

	public void setExpIcon(String expIcon) {
		this.expIcon = expIcon;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public int getProductId() {
		return productId;
	}

	public String getEnglishName() {
		return englishName;
	}

	public String getExpansion() {
		return expansion;
	}
}
