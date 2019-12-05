package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.ics.cardmarket4j.entity.PriceGuide;
import de.ics.cardmarket4j.utils.JsonIO;

public class PriceGuideDeserializer  implements JsonDeserializer<PriceGuide>{

	@Override
	public PriceGuide deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();
		
		BigDecimal sell = JsonIO.parseBigDecimal(jObject, "SELL");
		BigDecimal low = JsonIO.parseBigDecimal(jObject, "LOW");
		BigDecimal lowExPlus = JsonIO.parseBigDecimal(jObject, "LOWEX+");
		BigDecimal lowFoil = JsonIO.parseBigDecimal(jObject, "LOWFOIL");
		BigDecimal avg = JsonIO.parseBigDecimal(jObject, "AVG");
		BigDecimal trend = JsonIO.parseBigDecimal(jObject, "TREND");
		
		return new PriceGuide(sell, low, lowExPlus, lowFoil, avg, trend);
	}

}
