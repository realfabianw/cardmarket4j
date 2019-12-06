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

		int productId = JsonIO.parseInteger(jObject, "idProduct");
		int metaproductId = JsonIO.parseInteger(jObject, "idMetaproduct");
		int totalReprints = JsonIO.parseInteger(jObject, "countReprints");
		String name = JsonIO.parseString(jObject, "enName");
		Map<LanguageCode, String> mapLocalizedNames = new HashMap<>();

		for (JsonElement jElement : jObject.get("localization").getAsJsonArray()) {
			JsonObject jLoc = jElement.getAsJsonObject();
			mapLocalizedNames.put(CardMarketUtils.fromLanguageId(JsonIO.parseInteger(jLoc, "idLanguage")),
					JsonIO.parseString(jLoc, "name"));
		}

		Integer categoryId = null;
		if (jObject.keySet().contains("category")) {
			categoryId = JsonIO.parseInteger(jObject.get("category").getAsJsonObject(), "idCategory");
		}

		Integer categoryName = JsonIO.parseInteger(jObject, "categoryName");
		String selfUrl = JsonIO.parseString(jObject, "website");
		String imageUrl = JsonIO.parseString(jObject, "image");

		Game game;

		try {
			game = Game.parseValue(JsonIO.parseString(jObject, "gameName"));
		} catch (IllegalArgumentException e) {
			game = Game.parseId(JsonIO.parseInteger(jObject, "idGame"));
		}

		String expansionCollectionNumber;
		if (jObject.keySet().contains("number")) {
			expansionCollectionNumber = JsonIO.parseString(jObject, "number");
		} else {
			expansionCollectionNumber = JsonIO.parseString(jObject, "nr");
		}

		String rarity = JsonIO.parseString(jObject, "rarity");
		String expansionName = (JsonIO.parseString(jObject, "expansionName") != null
				? JsonIO.parseString(jObject, "expansionName")
				: JsonIO.parseString(jObject, "expansion"));

		Expansion expansion = JsonIO.getGson().fromJson(jObject.get("expansion"), Expansion.class);

		PriceGuide priceGuide = JsonIO.getGson().fromJson(jObject.get("priceGuide"), PriceGuide.class);

		List<Integer> listReprintProductIds = new ArrayList<>();
		if (jObject.keySet().contains("reprint")) {
			for (JsonElement jElement : jObject.get("reprint").getAsJsonArray()) {
				JsonObject jReprint = jElement.getAsJsonObject();
				listReprintProductIds.add(JsonIO.parseInteger(jReprint, "idProduct"));
			}
		}

		return new Product(productId, metaproductId, totalReprints, name, mapLocalizedNames, categoryId, categoryName,
				selfUrl, imageUrl, game, expansionCollectionNumber, rarity, expansionName, expansion, priceGuide,
				listReprintProductIds);
	}

}
