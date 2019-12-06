package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.ics.cardmarket4j.entity.BankAccount;
import de.ics.cardmarket4j.util.JsonIO;

public class BankAccountDeserializer implements JsonDeserializer<BankAccount> {

	@Override
	public BankAccount deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		String registeredOwner = JsonIO.parseString(jObject, "accountOwner");
		String iban = JsonIO.parseString(jObject, "iban");
		String bic = JsonIO.parseString(jObject, "bic");
		String bankName = JsonIO.parseString(jObject, "bankName");

		return new BankAccount(registeredOwner, iban, bic, bankName);
	}

}
