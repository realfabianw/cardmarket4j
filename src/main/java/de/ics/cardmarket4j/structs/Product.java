package de.ics.cardmarket4j.structs;

import de.ics.cardmarket4j.enums.Game;

public class Product {
	private final int productId;
	private int metaProductId;
	private int amountOfReprints;
	private Game game;
	private String englishName;
	private String localizedName;
	private String imageUrl;
	private String expansion;
	private String nr;
	private String expIcon;
	private String rarity;

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

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
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

	public String getExpansion() {
		return expansion;
	}

	public void setExpansion(String expansion) {
		this.expansion = expansion;
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

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", " + (game != null ? "game=" + game + ", " : "")
				+ (englishName != null ? "englishName=" + englishName + ", " : "")
				+ (localizedName != null ? "localizedName=" + localizedName + ", " : "")
				+ (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "")
				+ (expansion != null ? "expansion=" + expansion + ", " : "") + (nr != null ? "nr=" + nr + ", " : "")
				+ (expIcon != null ? "expIcon=" + expIcon + ", " : "") + (rarity != null ? "rarity=" + rarity : "")
				+ "]";
	}

}
