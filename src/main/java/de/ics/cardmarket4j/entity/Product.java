package de.ics.cardmarket4j.entity;

import java.util.List;
import java.util.Map;

import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.entity.enumeration.Game;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Product
 * @author QUE
 *
 */
public class Product {
	private Integer productId;
	private Integer metaproductId;
	private Integer totalReprints;
	private String name;
	private Map<LanguageCode, String> mapLocalizedNames;
	private Integer categoryId;
	private Integer categoryName;
	private String selfUrl;
	private String imageUrl;
	private Game game;
	private String expansionCollectionNumber;
	private String rarity;
	private String expansionName;
	private Expansion expansion;
	private PriceGuide priceGuide;
	private List<Integer> listReprintProductIds;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getMetaproductId() {
		return metaproductId;
	}

	public void setMetaproductId(Integer metaproductId) {
		this.metaproductId = metaproductId;
	}

	public Integer getTotalReprints() {
		return totalReprints;
	}

	public void setTotalReprints(Integer totalReprints) {
		this.totalReprints = totalReprints;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<LanguageCode, String> getMapLocalizedNames() {
		return mapLocalizedNames;
	}

	public void setMapLocalizedNames(Map<LanguageCode, String> mapLocalizedNames) {
		this.mapLocalizedNames = mapLocalizedNames;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(Integer categoryName) {
		this.categoryName = categoryName;
	}

	public String getSelfUrl() {
		return selfUrl;
	}

	public void setSelfUrl(String selfUrl) {
		this.selfUrl = selfUrl;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getExpansionCollectionNumber() {
		return expansionCollectionNumber;
	}

	public void setExpansionCollectionNumber(String expansionCollectionNumber) {
		this.expansionCollectionNumber = expansionCollectionNumber;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}

	public String getExpansionName() {
		return expansionName;
	}

	public void setExpansionName(String expansionName) {
		this.expansionName = expansionName;
	}

	public Expansion getExpansion() {
		return expansion;
	}

	public void setExpansion(Expansion expansion) {
		this.expansion = expansion;
	}

	public PriceGuide getPriceGuide() {
		return priceGuide;
	}

	public void setPriceGuide(PriceGuide priceGuide) {
		this.priceGuide = priceGuide;
	}

	public List<Integer> getListReprintProductIds() {
		return listReprintProductIds;
	}

	public void setListReprintProductIds(List<Integer> listReprintProductIds) {
		this.listReprintProductIds = listReprintProductIds;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
		result = prime * result + ((categoryName == null) ? 0 : categoryName.hashCode());
		result = prime * result + ((expansion == null) ? 0 : expansion.hashCode());
		result = prime * result + ((expansionCollectionNumber == null) ? 0 : expansionCollectionNumber.hashCode());
		result = prime * result + ((expansionName == null) ? 0 : expansionName.hashCode());
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
		result = prime * result + ((listReprintProductIds == null) ? 0 : listReprintProductIds.hashCode());
		result = prime * result + ((mapLocalizedNames == null) ? 0 : mapLocalizedNames.hashCode());
		result = prime * result + ((metaproductId == null) ? 0 : metaproductId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((priceGuide == null) ? 0 : priceGuide.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((rarity == null) ? 0 : rarity.hashCode());
		result = prime * result + ((selfUrl == null) ? 0 : selfUrl.hashCode());
		result = prime * result + ((totalReprints == null) ? 0 : totalReprints.hashCode());
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
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
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
		if (metaproductId == null) {
			if (other.metaproductId != null)
				return false;
		} else if (!metaproductId.equals(other.metaproductId))
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
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
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
		if (totalReprints == null) {
			if (other.totalReprints != null)
				return false;
		} else if (!totalReprints.equals(other.totalReprints))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [" + (productId != null ? "productId=" + productId + ", " : "")
				+ (metaproductId != null ? "metaproductId=" + metaproductId + ", " : "")
				+ (totalReprints != null ? "totalReprints=" + totalReprints + ", " : "")
				+ (name != null ? "name=" + name + ", " : "")
				+ (mapLocalizedNames != null ? "mapLocalizedNames=" + mapLocalizedNames + ", " : "")
				+ (categoryId != null ? "categoryId=" + categoryId + ", " : "")
				+ (categoryName != null ? "categoryName=" + categoryName + ", " : "")
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

	public Product(Integer productId, Integer metaproductId, Integer totalReprints, String name,
			Map<LanguageCode, String> mapLocalizedNames, Integer categoryId, Integer categoryName, String selfUrl,
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
}
