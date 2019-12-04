package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.neovisionaries.i18n.CountryCode;

import de.ics.cardmarket4j.entity.Account;
import de.ics.cardmarket4j.entity.BankAccount;
import de.ics.cardmarket4j.entity.User;
import de.ics.cardmarket4j.entity.enumeration.DeliverySpeed;
import de.ics.cardmarket4j.entity.enumeration.SellerActivationStatus;
import de.ics.cardmarket4j.utils.JsonIO;

public class AccountDeserializer implements JsonDeserializer<Account> {

	@Override
	public Account deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		User user = JsonIO.getGson().fromJson(json, User.class);
		JsonObject jObject = json.getAsJsonObject();

		CountryCode country;
		if (JsonIO.parseString(jObject, "country").equals("D")) {
			country = CountryCode.DE;
		} else {
			country = CountryCode.getByCode(JsonIO.parseString(jObject, "country"));
		}
		boolean maySell = JsonIO.parseBoolean(jObject, "maySell");
		SellerActivationStatus sellerActivationStatus = SellerActivationStatus
				.parseId(JsonIO.parseInteger(jObject, "sellerActivation"));
		DeliverySpeed deliverySpeed = DeliverySpeed.parseId(JsonIO.parseInteger(jObject, "shipsFast"));
		boolean activated = JsonIO.parseBoolean(jObject, "isActivated");
		BigDecimal totalBalance = JsonIO.parseBigDecimal(jObject.get("moneyDetails").getAsJsonObject(), "totalBalance");
		BigDecimal moneyBalance = JsonIO.parseBigDecimal(jObject.get("moneyDetails").getAsJsonObject(), "moneyBalance");
		BigDecimal bonusBalance = JsonIO.parseBigDecimal(jObject.get("moneyDetails").getAsJsonObject(), "bonusBalance");
		BigDecimal unpaidAmount = JsonIO.parseBigDecimal(jObject.get("moneyDetails").getAsJsonObject(), "unpaidAmount");
		BigDecimal providerRechargeAmount = JsonIO.parseBigDecimal(jObject.get("moneyDetails").getAsJsonObject(),
				"providerRechargeAmounT");
		BankAccount bankAccount = JsonIO.getGson().fromJson(jObject.get("bankAccount"), BankAccount.class);
		// new BankAccount(jObject.get("bankAccount").getAsJsonObject());
		int amountItemsInShoppingCard = JsonIO.parseInteger(jObject, "articlesInShoppingCart");
		int amountUnreadMessages = JsonIO.parseInteger(jObject, "unreadMessages");

		return new Account(user.getUserId(), user.getUserName(), user.getRegistrationDate(), user.getUserType(),
				user.isSeller(), user.getCompanyName(), user.getFirstName(), user.getLastName(), user.getAddress(),
				user.getPhoneNumber(), user.getEmailAddress(), user.getVat(), user.getRiskGroup(), user.getReputation(),
				user.getExpectedDeliveryTime(), user.getAmountSales(), user.getAmountSoldItems(),
				user.getAverageShippingTime(), user.isOnVacation(), country, maySell, sellerActivationStatus,
				deliverySpeed, activated, totalBalance, moneyBalance, bonusBalance, unpaidAmount,
				providerRechargeAmount, bankAccount, amountItemsInShoppingCard, amountUnreadMessages);
	}

}
