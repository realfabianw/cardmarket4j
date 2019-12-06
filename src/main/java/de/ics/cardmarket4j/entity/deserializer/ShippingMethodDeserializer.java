package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.ics.cardmarket4j.entity.ShippingMethod;
import de.ics.cardmarket4j.util.JsonIO;

public class ShippingMethodDeserializer implements JsonDeserializer<ShippingMethod> {

	@Override
	public ShippingMethod deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		int shippingMethodId = JsonIO.parseInteger(jObject, "idShippingMethod");
		String name = JsonIO.parseString(jObject, "name");
		BigDecimal price = JsonIO.parseBigDecimal(jObject, "price");
		boolean isLetter = JsonIO.parseBoolean(jObject, "isLetter");
		boolean isInsured = JsonIO.parseBoolean(jObject, "isInsured");

		return new ShippingMethod(shippingMethodId, name, price, isLetter, isInsured);
	}

}
