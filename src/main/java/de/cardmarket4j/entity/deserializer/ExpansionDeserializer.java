package de.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.neovisionaries.i18n.LanguageCode;

import de.cardmarket4j.entity.Expansion;
import de.cardmarket4j.entity.enumeration.Game;
import de.cardmarket4j.util.CardMarketUtils;
import de.cardmarket4j.util.JsonIO;

public class ExpansionDeserializer implements JsonDeserializer<Expansion> {

	@Override
	public Expansion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		int expansionId = JsonIO.parseInteger(jObject, "idExpansion");
		String name = JsonIO.parseString(jObject, "enName");
		Map<LanguageCode, String> mapLocalizedNames = new HashMap<>();
		if (jObject.keySet().contains("localization")) {
			for (JsonElement jElement : jObject.get("localization").getAsJsonArray()) {
				JsonObject jLoc = jElement.getAsJsonObject();
				mapLocalizedNames.put(CardMarketUtils.fromLanguageId(JsonIO.parseInteger(jLoc, "idLanguage")),
						JsonIO.parseString(jLoc, "name"));
			}
		}
		String code = JsonIO.parseString(jObject, "abbreviation");
		Integer iconCode = JsonIO.parseInteger(jObject, "icon");
		LocalDateTime releaseDate = JsonIO.parseLocalDateTime(jObject, "releaseDate", DateTimeFormatter.ISO_DATE_TIME);

		Game game = Game.parseId(JsonIO.parseInteger(jObject, "idGame"));

		return new Expansion(expansionId, name, mapLocalizedNames, code, iconCode, releaseDate, game);
	}

}
