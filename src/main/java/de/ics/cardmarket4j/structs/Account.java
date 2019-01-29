package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

import de.ics.cardmarket4j.enums.DeliverySpeed;
import de.ics.cardmarket4j.enums.Reputation;
import de.ics.cardmarket4j.enums.RiskGroup;
import de.ics.cardmarket4j.enums.SellerActivationStatus;
import de.ics.cardmarket4j.enums.UserType;

public class Account {
	private static Logger LOGGER = LoggerFactory.getLogger("Account");
	private int userId;
	private String userName;
	private CountryCode country;
	private UserType userType;
	private boolean maySell;
	private SellerActivationStatus sellerActivationStatus;
	private RiskGroup riskGroup;
	private Reputation reputation;
	private DeliverySpeed deliverySpeed;
	private int sellCount;
	private int soldItems;
	private int avgShippingTime;
	private boolean onVacation;
	private String lastName;
	private String firstName;
	private Address address;
	private String email;
	private String phoneNumber;
	private String vat;
	private LocalDateTime registerDate;
	private boolean isActivated;
	private BigDecimal totalBalance;
	private BigDecimal moneyBalance;
	private BigDecimal bonusBalance;
	private BigDecimal unpaidAmount;
	private BigDecimal providerRechargeAmount;
	private BankAccount bankAccount;
	private int itemsInShoppingCard;
	private int unreadMessages;

	public Account(JsonElement jAccount) {
		LOGGER.info(jAccount.toString());
		JsonObject j = jAccount.getAsJsonObject().get("account").getAsJsonObject();

		this.userId = j.get("idUser").getAsInt();
		this.userName = j.get("username").getAsString();
		if (!j.get("country").getAsString().equals("D")) {
			this.country = CountryCode.getByCode(j.get("country").getAsString());
		} else {
			this.country = CountryCode.DE;
		}
		this.userType = UserType.parseId(j.get("isCommercial").getAsInt());
		this.maySell = j.get("maySell").getAsBoolean();
		this.sellerActivationStatus = SellerActivationStatus.parseId(j.get("sellerActivation").getAsInt());
		this.riskGroup = RiskGroup.parseId(j.get("riskGroup").getAsInt());
		this.reputation = Reputation.parseId(j.get("reputation").getAsInt());
		this.deliverySpeed = DeliverySpeed.parseId(j.get("shipsFast").getAsInt());
		this.sellCount = j.get("sellCount").getAsInt();
		this.soldItems = j.get("soldItems").getAsInt();
		this.avgShippingTime = j.get("avgShippingTime").getAsInt();
		this.onVacation = j.get("onVacation").getAsBoolean();
		this.lastName = j.get("name").getAsJsonObject().get("lastName").getAsString();
		this.firstName = j.get("name").getAsJsonObject().get("firstName").getAsString();
		this.address = new Address(j.get("homeAddress").getAsJsonObject());
		this.email = j.get("email").getAsString();
		this.phoneNumber = j.get("phoneNumber").getAsString();
		try {
			this.vat = j.get("vat").getAsString();
		} catch (UnsupportedOperationException e) {
			this.vat = "";
		}
		String registrationDate = j.get("registerDate").getAsString();
		registrationDate = registrationDate.split("\\+0[0-9]")[0] + "+0" + registrationDate.split("\\+0")[1].charAt(0)
				+ ":" + registrationDate.split("\\+0[0-9]")[1];
		this.registerDate = LocalDateTime.parse(registrationDate, DateTimeFormatter.ISO_DATE_TIME);
		this.isActivated = j.get("isActivated").getAsBoolean();
		this.totalBalance = j.get("moneyDetails").getAsJsonObject().get("totalBalance").getAsBigDecimal();
		this.moneyBalance = j.get("moneyDetails").getAsJsonObject().get("moneyBalance").getAsBigDecimal();
		this.bonusBalance = j.get("moneyDetails").getAsJsonObject().get("bonusBalance").getAsBigDecimal();
		this.unpaidAmount = j.get("moneyDetails").getAsJsonObject().get("unpaidAmount").getAsBigDecimal();
		this.providerRechargeAmount = j.get("moneyDetails").getAsJsonObject().get("providerRechargeAmount")
				.getAsBigDecimal();
		this.bankAccount = new BankAccount(j);
		this.itemsInShoppingCard = j.get("articlesInShoppingCart").getAsInt();
		this.unreadMessages = j.get("unreadMessages").getAsInt();
	}

	@Override
	public String toString() {
		return "Account [userId=" + userId + ", userName=" + userName + ", country=" + country + ", userType="
				+ userType + ", maySell=" + maySell + ", sellerActivationStatus=" + sellerActivationStatus
				+ ", riskGroup=" + riskGroup + ", reputation=" + reputation + ", deliverySpeed=" + deliverySpeed
				+ ", sellCount=" + sellCount + ", soldItems=" + soldItems + ", avgShippingTime=" + avgShippingTime
				+ ", onVacation=" + onVacation + ", lastName=" + lastName + ", firstName=" + firstName + ", address="
				+ address + ", email=" + email + ", phoneNumber=" + phoneNumber + ", vat=" + vat + ", registerDate="
				+ registerDate + ", isActivated=" + isActivated + ", totalBalance=" + totalBalance + ", moneyBalance="
				+ moneyBalance + ", bonusBalance=" + bonusBalance + ", unpaidAmount=" + unpaidAmount
				+ ", providerRechargeAmount=" + providerRechargeAmount + ", bankAccount=" + bankAccount
				+ ", itemsInShoppingCard=" + itemsInShoppingCard + ", unreadMessages=" + unreadMessages + "]";
	}

}
