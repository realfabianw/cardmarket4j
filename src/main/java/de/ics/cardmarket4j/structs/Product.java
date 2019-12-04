package de.ics.cardmarket4j.structs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.CardMarketUtils;
import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.enums.Game;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Product
 * @author QUE
 *
 */
public class Product {
	private String jsonString;
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
	// Links here
	private Expansion expansion;
	private PriceGuide priceGuide;
	private List<Integer> listReprintProductIds;

	public Product(int productId, JsonObject jObject) {
		this.jsonString = jObject.toString();
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
		this.jsonString = jObject.toString();
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

	public Product(String jsonString, int productId, int metaproductId, int totalReprints, String name,
			Map<LanguageCode, String> mapLocalizedNames, int categoryId, int categoryName, String selfUrl,
			String imageUrl, Game game, String expansionCollectionNumber, String rarity, String expansionName,
			Expansion expansion, PriceGuide priceGuide, List<Integer> listReprintProductIds) {
		this.jsonString = jsonString;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName != other.categoryName)
			return false;
		if (expansion == null) {
			if (other.expansion != null)
				return false;
		} else if (!expansion.equals(other.expansion))
			return false;
		if (expansionCollectionNumber == null) {
			if (other.expansionCollectionNumber != null)
				return false;
		} else if (!expansionCollectionNumber.equals(other.expansionCollectionNumber))
			return false;
		if (expansionName == null) {
			if (other.expansionName != null)
				return false;
		} else if (!expansionName.equals(other.expansionName))
			return false;
		if (game != other.game)
			return false;
		if (imageUrl == null) {
			if (other.imageUrl != null)
				return false;
		} else if (!imageUrl.equals(other.imageUrl))
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
			return false;
		if (listReprintProductIds == null) {
			if (other.listReprintProductIds != null)
				return false;
		} else if (!listReprintProductIds.equals(other.listReprintProductIds))
			return false;
		if (mapLocalizedNames == null) {
			if (other.mapLocalizedNames != null)
				return false;
		} else if (!mapLocalizedNames.equals(other.mapLocalizedNames))
			return false;
		if (metaproductId != other.metaproductId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priceGuide == null) {
			if (other.priceGuide != null)
				return false;
		} else if (!priceGuide.equals(other.priceGuide))
			return false;
		if (productId != other.productId)
			return false;
		if (rarity == null) {
			if (other.rarity != null)
				return false;
		} else if (!rarity.equals(other.rarity))
			return false;
		if (selfUrl == null) {
			if (other.selfUrl != null)
				return false;
		} else if (!selfUrl.equals(other.selfUrl))
			return false;
		if (totalReprints != other.totalReprints)
			return false;
		return true;
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

	public String getJsonString() {
		return jsonString;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result + categoryName;
		result = prime * result + ((expansion == null) ? 0 : expansion.hashCode());
		result = prime * result + ((expansionCollectionNumber == null) ? 0 : expansionCollectionNumber.hashCode());
		result = prime * result + ((expansionName == null) ? 0 : expansionName.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
		result = prime * result + ((listReprintProductIds == null) ? 0 : listReprintProductIds.hashCode());
		result = prime * result + ((mapLocalizedNames == null) ? 0 : mapLocalizedNames.hashCode());
		result = prime * result + metaproductId;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priceGuide == null) ? 0 : priceGuide.hashCode());
		result = prime * result + productId;
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((selfUrl == null) ? 0 : selfUrl.hashCode());
		result = prime * result + totalReprints;
		return result;
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

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
	public String toString() {
		return "Product [" + (jsonString != null ? "jsonString=" + jsonString + ", " : "") + "productId=" + productId
				+ ", metaproductId=" + metaproductId + ", totalReprints=" + totalReprints + ", "
				+ (name != null ? "name=" + name + ", " : "")
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
