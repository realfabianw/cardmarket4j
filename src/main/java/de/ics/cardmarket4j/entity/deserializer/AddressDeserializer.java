package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

import de.ics.cardmarket4j.entity.Address;
import de.ics.cardmarket4j.utils.JsonIO;

public class AddressDeserializer implements JsonDeserializer<Address> {
	@Override
	public Address deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject jObject = json.getAsJsonObject();

		String name = JsonIO.parseString(jObject, "name");
		String extra = JsonIO.parseString(jObject, "extra");
		String street = JsonIO.parseString(jObject, "street");
		String zip = JsonIO.parseString(jObject, "zip");
		String city = JsonIO.parseString(jObject, "city");
		CountryCode country;
		if (!JsonIO.parseString(jObject, "country").equals("D")) {
			country = CountryCode.getByCode(JsonIO.parseString(jObject, "country"));
		} else {
			country = CountryCode.DE;
		}
		return new Address(name, extra, street, zip, city, country);
	}
}
