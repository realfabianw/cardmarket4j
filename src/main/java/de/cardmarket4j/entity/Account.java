package de.cardmarket4j.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.neovisionaries.i18n.CountryCode;

import de.cardmarket4j.entity.enumeration.DeliverySpeed;
import de.cardmarket4j.entity.enumeration.Reputation;
import de.cardmarket4j.entity.enumeration.RiskGroup;
import de.cardmarket4j.entity.enumeration.SellerActivationStatus;
import de.cardmarket4j.entity.enumeration.UserType;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Account
 * @author QUE
 *
 */
public class Account extends User {
	private CountryCode country;
	private boolean maySell;
	private SellerActivationStatus sellerActivationStatus;
	private DeliverySpeed deliverySpeed;
	private boolean activated;
	private BigDecimal totalBalance;
	private BigDecimal moneyBalance;
	private BigDecimal bonusBalance;
	private BigDecimal unpaidAmount;
	private BigDecimal providerRechargeAmount;
	private BankAccount bankAccount;
	private int amountItemsInShoppingCard;
	private int amountUnreadMessages;

	public Account(int userId, String userName, LocalDateTime registrationDate, UserType userType, boolean seller,
			String companyName, String firstName, String lastName, Address address, String phoneNumber,
			String emailAddress, String vat, RiskGroup riskGroup, Reputation reputation, int expectedDeliveryTime,
			int amountSales, int amountSoldItems, int averageShippingTime, boolean onVacation, CountryCode country,
			boolean maySell, SellerActivationStatus sellerActivationStatus, DeliverySpeed deliverySpeed,
			boolean activated, BigDecimal totalBalance, BigDecimal moneyBalance, BigDecimal bonusBalance,
			BigDecimal unpaidAmount, BigDecimal providerRechargeAmount, BankAccount bankAccount,
			int amountItemsInShoppingCard, int amountUnreadMessages) {
		super(userId, userName, registrationDate, userType, seller, companyName, firstName, lastName, address,
				phoneNumber, emailAddress, vat, riskGroup, reputation, expectedDeliveryTime, amountSales,
				amountSoldItems, averageShippingTime, onVacation);
		this.country = country;
		this.maySell = maySell;
		this.sellerActivationStatus = sellerActivationStatus;
		this.deliverySpeed = deliverySpeed;
		this.activated = activated;
		this.totalBalance = totalBalance;
		this.moneyBalance = moneyBalance;
		this.bonusBalance = bonusBalance;
		this.unpaidAmount = unpaidAmount;
		this.providerRechargeAmount = providerRechargeAmount;
		this.bankAccount = bankAccount;
		this.amountItemsInShoppingCard = amountItemsInShoppingCard;
		this.amountUnreadMessages = amountUnreadMessages;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (activated != other.activated)
			return false;
		if (amountItemsInShoppingCard != other.amountItemsInShoppingCard)
			return false;
		if (amountUnreadMessages != other.amountUnreadMessages)
			return false;
		if (bankAccount == null) {
			if (other.bankAccount != null)
				return false;
		} else if (!bankAccount.equals(other.bankAccount))
			return false;
		if (bonusBalance == null) {
			if (other.bonusBalance != null)
				return false;
		} else if (!bonusBalance.equals(other.bonusBalance))
			return false;
		if (country != other.country)
			return false;
		if (deliverySpeed != other.deliverySpeed)
			return false;
		if (maySell != other.maySell)
			return false;
		if (moneyBalance == null) {
			if (other.moneyBalance != null)
				return false;
		} else if (!moneyBalance.equals(other.moneyBalance))
			return false;
		if (providerRechargeAmount == null) {
			if (other.providerRechargeAmount != null)
				return false;
		} else if (!providerRechargeAmount.equals(other.providerRechargeAmount))
			return false;
		if (sellerActivationStatus != other.sellerActivationStatus)
			return false;
		if (totalBalance == null) {
			if (other.totalBalance != null)
				return false;
		} else if (!totalBalance.equals(other.totalBalance))
			return false;
		if (unpaidAmount == null) {
			if (other.unpaidAmount != null)
				return false;
		} else if (!unpaidAmount.equals(other.unpaidAmount))
			return false;
		return true;
	}

	public int getAmountItemsInShoppingCard() {
		return amountItemsInShoppingCard;
	}

	public int getAmountUnreadMessages() {
		return amountUnreadMessages;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public BigDecimal getBonusBalance() {
		return bonusBalance;
	}

	public CountryCode getCountry() {
		return country;
	}

	public DeliverySpeed getDeliverySpeed() {
		return deliverySpeed;
	}

	public BigDecimal getMoneyBalance() {
		return moneyBalance;
	}

	public BigDecimal getProviderRechargeAmount() {
		return providerRechargeAmount;
	}

	public SellerActivationStatus getSellerActivationStatus() {
		return sellerActivationStatus;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public BigDecimal getUnpaidAmount() {
		return unpaidAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (activated ? 1231 : 1237);
		result = prime * result + amountItemsInShoppingCard;
		result = prime * result + amountUnreadMessages;
		result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
		result = prime * result + ((bonusBalance == null) ? 0 : bonusBalance.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((deliverySpeed == null) ? 0 : deliverySpeed.hashCode());
		result = prime * result + (maySell ? 1231 : 1237);
		result = prime * result + ((moneyBalance == null) ? 0 : moneyBalance.hashCode());
		result = prime * result + ((providerRechargeAmount == null) ? 0 : providerRechargeAmount.hashCode());
		result = prime * result + ((sellerActivationStatus == null) ? 0 : sellerActivationStatus.hashCode());
		result = prime * result + ((totalBalance == null) ? 0 : totalBalance.hashCode());
		result = prime * result + ((unpaidAmount == null) ? 0 : unpaidAmount.hashCode());
		return result;
	}

	public boolean isActivated() {
		return activated;
	}

	public boolean isMaySell() {
		return maySell;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public void setAmountItemsInShoppingCard(int amountItemsInShoppingCard) {
		this.amountItemsInShoppingCard = amountItemsInShoppingCard;
	}

	public void setAmountUnreadMessages(int amountUnreadMessages) {
		this.amountUnreadMessages = amountUnreadMessages;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public void setBonusBalance(BigDecimal bonusBalance) {
		this.bonusBalance = bonusBalance;
	}

	public void setCountry(CountryCode country) {
		this.country = country;
	}

	public void setDeliverySpeed(DeliverySpeed deliverySpeed) {
		this.deliverySpeed = deliverySpeed;
	}

	public void setMaySell(boolean maySell) {
		this.maySell = maySell;
	}

	public void setMoneyBalance(BigDecimal moneyBalance) {
		this.moneyBalance = moneyBalance;
	}

	public void setProviderRechargeAmount(BigDecimal providerRechargeAmount) {
		this.providerRechargeAmount = providerRechargeAmount;
	}

	public void setSellerActivationStatus(SellerActivationStatus sellerActivationStatus) {
		this.sellerActivationStatus = sellerActivationStatus;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}

	public void setUnpaidAmount(BigDecimal unpaidAmount) {
		this.unpaidAmount = unpaidAmount;
	}

	@Override
	public String toString() {
		return "Account [" + (country != null ? "country=" + country + ", " : "") + "maySell=" + maySell + ", "
				+ (sellerActivationStatus != null ? "sellerActivationStatus=" + sellerActivationStatus + ", " : "")
				+ (deliverySpeed != null ? "deliverySpeed=" + deliverySpeed + ", " : "") + "activated=" + activated
				+ ", " + (totalBalance != null ? "totalBalance=" + totalBalance + ", " : "")
				+ (moneyBalance != null ? "moneyBalance=" + moneyBalance + ", " : "")
				+ (bonusBalance != null ? "bonusBalance=" + bonusBalance + ", " : "")
				+ (unpaidAmount != null ? "unpaidAmount=" + unpaidAmount + ", " : "")
				+ (providerRechargeAmount != null ? "providerRechargeAmount=" + providerRechargeAmount + ", " : "")
				+ (bankAccount != null ? "bankAccount=" + bankAccount + ", " : "") + "amountItemsInShoppingCard="
				+ amountItemsInShoppingCard + ", amountUnreadMessages=" + amountUnreadMessages + ", "
				+ (getAddress() != null ? "getAddress()=" + getAddress() + ", " : "") + "getAmountSales()="
				+ getAmountSales() + ", getAmountSoldItems()=" + getAmountSoldItems() + ", getAverageShippingTime()="
				+ getAverageShippingTime() + ", "
				+ (getCompanyName() != null ? "getCompanyName()=" + getCompanyName() + ", " : "")
				+ (getEmailAddress() != null ? "getEmailAddress()=" + getEmailAddress() + ", " : "")
				+ "getExpectedDeliveryTime()=" + getExpectedDeliveryTime() + ", "
				+ (getFirstName() != null ? "getFirstName()=" + getFirstName() + ", " : "")
				+ (getLastName() != null ? "getLastName()=" + getLastName() + ", " : "")
				+ (getPhoneNumber() != null ? "getPhoneNumber()=" + getPhoneNumber() + ", " : "")
				+ (getRegistrationDate() != null ? "getRegistrationDate()=" + getRegistrationDate() + ", " : "")
				+ (getReputation() != null ? "getReputation()=" + getReputation() + ", " : "")
				+ (getRiskGroup() != null ? "getRiskGroup()=" + getRiskGroup() + ", " : "") + "getUserId()="
				+ getUserId() + ", " + (getUserName() != null ? "getUserName()=" + getUserName() + ", " : "")
				+ (getUserType() != null ? "getUserType()=" + getUserType() + ", " : "")
				+ (getVat() != null ? "getVat()=" + getVat() + ", " : "") + "hashCode()=" + hashCode()
				+ ", isOnVacation()=" + isOnVacation() + ", isSeller()=" + isSeller() + ", "
				+ (super.toString() != null ? "toString()=" + super.toString() + ", " : "")
				+ (getClass() != null ? "getClass()=" + getClass() : "") + "]";
	}
}
