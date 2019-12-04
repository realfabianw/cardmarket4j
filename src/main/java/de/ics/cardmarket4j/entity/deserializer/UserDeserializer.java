package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.ics.cardmarket4j.entity.Address;
import de.ics.cardmarket4j.entity.User;
import de.ics.cardmarket4j.entity.enumeration.Reputation;
import de.ics.cardmarket4j.entity.enumeration.RiskGroup;
import de.ics.cardmarket4j.entity.enumeration.UserType;
import de.ics.cardmarket4j.utils.JsonIO;

public class UserDeserializer implements JsonDeserializer<User> {

	@Override
	public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		int userId = JsonIO.parseInteger(jObject, "idUser");
		String userName = JsonIO.parseString(jObject, "username");
		// Account == registerDate, User == registrationDate
		String dateString = JsonIO.parseString(jObject, "registrationDate") == null
				? JsonIO.parseString(jObject, "registerDate")
				: JsonIO.parseString(jObject, "registrationDate");
		dateString = dateString.split("\\+0[0-9]")[0] + "+0" + dateString.split("\\+0")[1].charAt(0) + ":"
				+ dateString.split("\\+0[0-9]")[1];
		LocalDateTime registrationDate = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);

		UserType userType = UserType.parseId(JsonIO.parseInteger(jObject, "isCommercial"));
		boolean seller = JsonIO.parseBoolean(jObject, "isSeller");
		String companyName = JsonIO.parseString(jObject.get("name").getAsJsonObject(), "company");
		String firstName = JsonIO.parseString(jObject.get("name").getAsJsonObject(), "firstName");
		String lastName = JsonIO.parseString(jObject.get("name").getAsJsonObject(), "lastName");
		Address address;
		if (jObject.keySet().contains("address")) {
			address = JsonIO.getGson().fromJson(jObject.get("address"), Address.class);
		} else {
			address = JsonIO.getGson().fromJson(jObject.get("homeAddress"), Address.class);
		}
		String phoneNumber = JsonIO.parseString(jObject, "phone");
		String emailAddress = JsonIO.parseString(jObject, "email");
		String vat = JsonIO.parseString(jObject, "vat");
		RiskGroup riskGroup = RiskGroup.parseId(JsonIO.parseInteger(jObject, "riskGroup"));
		Reputation reputation = Reputation.parseId(JsonIO.parseInteger(jObject, "reputation"));
		int expectedDeliveryTime = JsonIO.parseInteger(jObject, "shipsFast");
		int amountSales = JsonIO.parseInteger(jObject, "sellCount");
		int amountSoldItems = JsonIO.parseInteger(jObject, "soldItems");
		int averageShippingTime = JsonIO.parseInteger(jObject, "avgShippingTime");
		boolean onVacation = JsonIO.parseBoolean(jObject, "onVacation");

		return new User(userId, userName, registrationDate, userType, seller, companyName, firstName, lastName, address,
				phoneNumber, emailAddress, vat, riskGroup, reputation, expectedDeliveryTime, amountSales,
				amountSoldItems, averageShippingTime, onVacation);
	}

}
