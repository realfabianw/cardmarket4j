package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.neovisionaries.i18n.LanguageCode;

import de.ics.cardmarket4j.entity.Expansion;
import de.ics.cardmarket4j.entity.PriceGuide;
import de.ics.cardmarket4j.entity.Product;
import de.ics.cardmarket4j.entity.enumeration.Game;
import de.ics.cardmarket4j.utils.CardMarketUtils;
import de.ics.cardmarket4j.utils.JsonIO;

public class ProductDeserializer implements JsonDeserializer<Product> {

	@Override
	public Product deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();
		
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
		
		
		int productId = JsonIO.parseInteger(jObject, "idProduct");
		int metaproductId = JsonIO.parseInteger(jObject, "idMetaproduct");
		int totalReprints = JsonIO.parseInteger(jObject, "countReprints");
		String name = JsonIO.parseString(jObject, "enName");
		Map<LanguageCode, String> mapLocalizedNames = new HashMap<>();
		try {
			for (JsonElement jElement : jObject.get("localization").getAsJsonArray()) {
				JsonObject jLoc = jElement.getAsJsonObject();
				mapLocalizedNames.put(CardMarketUtils.fromLanguageId(JsonIO.parseInteger(jLoc, "idLanguage")),
						JsonIO.parseString(jLoc, "name"));
			}
		} catch (NullPointerException e) {

		}
		try {
			int categoryId = JsonIO.parseInteger(jObject.get("category").getAsJsonObject(), "idCategory");
		} catch (NullPointerException e) {

		}
		int categoryName = JsonIO.parseInteger(jObject, "categoryName");
		String selfUrl = JsonIO.parseString(jObject, "website");
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
		
		return new Product(productId, metaproductId, totalReprints, name, mapLocalizedNames, categoryId, categoryName,
				selfUrl, imageUrl, game, expansionCollectionNumber, rarity, expansionName, expansion, priceGuide,
				listReprintProductIds);
	}

}
