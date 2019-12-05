package de.ics.cardmarket4j.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.entity.enumeration.Game;
import de.ics.cardmarket4j.utils.CardMarketUtils;
import de.ics.cardmarket4j.utils.JsonIO;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Product
 * @author QUE
 *
 */
public class Product {
	private int productId;
	private int metaproductId;
	private int totalReprints;
	private String name;
	private Map<LanguageCode, String> mapLocalizedNames;
	private int categoryId;
	private int categoryName;
	private String selfUrl;
	private String imageUrl;
	private Game game;
	private String expansionCollectionNumber;
	private String rarity;
	private String expansionName;
	private Expansion expansion;
	private PriceGuide priceGuide;
	private List<Integer> listReprintProductIds;

	public Product(int productId, JsonObject jObject) {
		this.productId = productId;
		this.metaproductId = JsonIO.parseInteger(jObject, "idMetaproduct");
		this.totalReprints = JsonIO.parseInteger(jObject, "countReprints");
		this.name = JsonIO.parseString(jObject, "enName");
		this.mapLocalizedNames = new HashMap<>();
		try {
			for (JsonElement jElement : jObject.get("localization").getAsJsonArray()) {
				JsonObject jLoc = jElement.getAsJsonObject();
				mapLocalizedNames.put(CardMarketUtils.fromLanguageId(JsonIO.parseInteger(jLoc, "idLanguage")),
						JsonIO.parseString(jLoc, "name"));
			}
		} catch (NullPointerException e) {

		}
		try {
			this.categoryId = JsonIO.parseInteger(jObject.get("category").getAsJsonObject(), "idCategory");
		} catch (NullPointerException e) {

		}
		this.categoryName = JsonIO.parseInteger(jObject, "categoryName");
		this.selfUrl = JsonIO.parseString(jObject, "website");
		this.imageUrl = JsonIO.parseString(jObject, "image");
		if (imageUrl.charAt(0) == '.') {
			this.imageUrl = "https://www.cardmarket.com" + imageUrl.substring(1);
		}
		try {
			this.game = Game.parseValue(JsonIO.parseString(jObject, "gameName"));
		} catch (IllegalArgumentException e) {
			// Constructor is called from Article Instance
			this.game = Game.parseId(JsonIO.parseInteger(jObject, "idGame"));
		}
		try {
			this.expansionCollectionNumber = JsonIO.parseString(jObject, "number");
			if (this.expansionCollectionNumber == null) {
				// TODO dirty fix. JsonIO muss Exceptions werfen anstatt null zu returnen
				this.expansionCollectionNumber = JsonIO.parseString(jObject, "nr");
			}
		} catch (NullPointerException e) {
			// Constructor is called from Article Instance
			this.expansionCollectionNumber = JsonIO.parseString(jObject, "nr");
		}
		this.rarity = JsonIO.parseString(jObject, "rarity");
		this.expansionName = (JsonIO.parseString(jObject, "expansionName") != null
				? JsonIO.parseString(jObject, "expansionName")
				: JsonIO.parseString(jObject, "expansion"));
		try {
			this.expansion = new Expansion(jObject.get("expansion").getAsJsonObject());
		} catch (IllegalStateException | NullPointerException e) {
			// Constructor is called from Article Instance
		}
		try {
			this.priceGuide = new PriceGuide(jObject.get("priceGuide").getAsJsonObject());
		} catch (NullPointerException e) {
			// Constructor is called from Article Instance
		}
		this.listReprintProductIds = new ArrayList<>();
		try {
			for (JsonElement jElement : jObject.get("reprint").getAsJsonArray()) {
				JsonObject jReprint = jElement.getAsJsonObject();
				listReprintProductIds.add(JsonIO.parseInteger(jReprint, "idProduct"));
			}
		} catch (NullPointerException e) {
			// Constructor is called from Article Instance
		}
	}

	public Product(JsonObject jObject) {
		this.productId = JsonIO.parseInteger(jObject, "idProduct");
		this.metaproductId = JsonIO.parseInteger(jObject, "idMetaproduct");
		this.totalReprints = JsonIO.parseInteger(jObject, "countReprints");
		this.name = JsonIO.parseString(jObject, "enName");
		this.mapLocalizedNames = new HashMap<>();
		try {
			for (JsonElement jElement : jObject.get("localization").getAsJsonArray()) {
				JsonObject jLoc = jElement.getAsJsonObject();
				mapLocalizedNames.put(CardMarketUtils.fromLanguageId(JsonIO.parseInteger(jLoc, "idLanguage")),
						JsonIO.parseString(jLoc, "name"));
			}
		} catch (NullPointerException e) {

		}
		try {
			this.categoryId = JsonIO.parseInteger(jObject.get("category").getAsJsonObject(), "idCategory");
		} catch (NullPointerException e) {

		}
		this.categoryName = JsonIO.parseInteger(jObject, "categoryName");
		this.selfUrl = JsonIO.parseString(jObject, "website");
		this.imageUrl = JsonIO.parseString(jObject, "image");
		try {
			this.game = Game.parseValue(JsonIO.parseString(jObject, "gameName"));
		} catch (IllegalArgumentException e) {
			// Constructor is called from Article Instance
			this.game = Game.parseId(JsonIO.parseInteger(jObject, "idGame"));
		}
		try {
			this.expansionCollectionNumber = JsonIO.parseString(jObject, "number");
		} catch (NullPointerException e) {
			// Constructor is called from Article Instance
			this.expansionCollectionNumber = JsonIO.parseString(jObject, "nr");
		}
		this.rarity = JsonIO.parseString(jObject, "rarity");
		this.expansionName = (JsonIO.parseString(jObject, "expansionName") != null
				? JsonIO.parseString(jObject, "expansionName")
				: JsonIO.parseString(jObject, "expansion"));
		try {
			this.expansion = new Expansion(jObject.get("expansion").getAsJsonObject());
		} catch (IllegalStateException | NullPointerException e) {
			// Constructor is called from Article Instance
		}
		try {
			this.priceGuide = new PriceGuide(jObject.get("priceGuide").getAsJsonObject());
		} catch (NullPointerException e) {
			// Constructor is called from Article Instance
		}
		this.listReprintProductIds = new ArrayList<>();
		try {
			for (JsonElement jElement : jObject.get("reprint").getAsJsonArray()) {
				JsonObject jReprint = jElement.getAsJsonObject();
				listReprintProductIds.add(JsonIO.parseInteger(jReprint, "idProduct"));
			}
		} catch (NullPointerException e) {
			// Constructor is called from Article Instance
		}
	}

	public Product(int productId, int metaproductId, int totalReprints, String name,
			Map<LanguageCode, String> mapLocalizedNames, int categoryId, int categoryName, String selfUrl,
			String imageUrl, Game game, String expansionCollectionNumber, String rarity, String expansionName,
			Expansion expansion, PriceGuide priceGuide, List<Integer> listReprintProductIds) {
		this.productId = productId;
		this.metaproductId = metaproductId;
		this.totalReprints = totalReprints;
		this.name = name;
		this.mapLocalizedNames = mapLocalizedNames;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.selfUrl = selfUrl;
		this.imageUrl = imageUrl;
		this.game = game;
		this.expansionCollectionNumber = expansionCollectionNumber;
		this.rarity = rarity;
		this.expansionName = expansionName;
		this.expansion = expansion;
		this.priceGuide = priceGuide;
		this.listReprintProductIds = listReprintProductIds;
	}



	public int getCategoryId() {
		return categoryId;
	}

	public int getCategoryName() {
		return categoryName;
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public String getExpansionCollectionNumber() {
		return expansionCollectionNumber;
	}

	public String getExpansionName() {
		return expansionName;
	}

	public Game getGame() {
		return game;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public List<Integer> getListReprintProductIds() {
		return listReprintProductIds;
	}

	public Map<LanguageCode, String> getMapLocalizedNames() {
		return mapLocalizedNames;
	}

	public int getMetaproductId() {
		return metaproductId;
	}

	public String getName() {
		return name;
	}

	public PriceGuide getPriceGuide() {
		return priceGuide;
	}

	public int getProductId() {
		return productId;
	}

	public String getRarity() {
		return rarity;
	}

	public String getSelfUrl() {
		return selfUrl;
	}

	public int getTotalReprints() {
		return totalReprints;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public void setCategoryName(int categoryName) {
		this.categoryName = categoryName;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	public void setExpansionCollectionNumber(String expansionCollectionNumber) {
		this.expansionCollectionNumber = expansionCollectionNumber;
	}

	public void setExpansionName(String expansionName) {
		this.expansionName = expansionName;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setListReprintProductIds(List<Integer> listReprintProductIds) {
		this.listReprintProductIds = listReprintProductIds;
	}

	public void setMapLocalizedNames(Map<LanguageCode, String> mapLocalizedNames) {
		this.mapLocalizedNames = mapLocalizedNames;
	}

	public void setMetaproductId(int metaproductId) {
		this.metaproductId = metaproductId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPriceGuide(PriceGuide priceGuide) {
		this.priceGuide = priceGuide;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public void setSelfUrl(String selfUrl) {
		this.selfUrl = selfUrl;
	}

	public void setTotalReprints(int totalReprints) {
		this.totalReprints = totalReprints;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productId != other.productId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", metaproductId=" + metaproductId + ", totalReprints="
				+ totalReprints + ", " + (name != null ? "name=" + name + ", " : "")
				+ (mapLocalizedNames != null ? "mapLocalizedNames=" + mapLocalizedNames + ", " : "") + "categoryId="
				+ categoryId + ", categoryName=" + categoryName + ", "
				+ (selfUrl != null ? "selfUrl=" + selfUrl + ", " : "")
				+ (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") + (game != null ? "game=" + game + ", " : "")
				+ (expansionCollectionNumber != null ? "expansionCollectionNumber=" + expansionCollectionNumber + ", "
						: "")
				+ (rarity != null ? "rarity=" + rarity + ", " : "")
				+ (expansionName != null ? "expansionName=" + expansionName + ", " : "")
				+ (expansion != null ? "expansion=" + expansion + ", " : "")
				+ (priceGuide != null ? "priceGuide=" + priceGuide + ", " : "")
				+ (listReprintProductIds != null ? "listReprintProductIds=" + listReprintProductIds : "") + "]";
	}
}
