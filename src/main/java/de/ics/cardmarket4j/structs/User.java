package de.ics.cardmarket4j.structs;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.enums.Reputation;
import de.ics.cardmarket4j.enums.RiskGroup;
import de.ics.cardmarket4j.enums.UserType;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:User
 * @author QUE
 *
 */
public class User {
	private String jsonString;
	private int userId;
	private String userName;
	private LocalDateTime registrationDate;
	private UserType userType;
	private boolean seller;
	private String companyName;
	private String firstName;
	private String lastName;
	private Address address;
	private String phoneNumber;
	private String emailAddress;
	private String vat;
	private RiskGroup riskGroup;
	private Reputation reputation;
	private int expectedDeliveryTime;
	private int amountSales;
	private int amountSoldItems;
	private int averageShippingTime;
	private boolean onVacation;

	public User(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.userId = JsonIO.parseInteger(jObject, "idUser");
		this.userName = JsonIO.parseString(jObject, "username");
		// Account == registerDate, User == registrationDate
		String registrationDate = JsonIO.parseString(jObject, "registrationDate") == null
				? JsonIO.parseString(jObject, "registerDate")
				: JsonIO.parseString(jObject, "registrationDate");
		registrationDate = registrationDate.split("\\+0[0-9]")[0] + "+0" + registrationDate.split("\\+0")[1].charAt(0)
				+ ":" + registrationDate.split("\\+0[0-9]")[1];
		this.registrationDate = LocalDateTime.parse(registrationDate, DateTimeFormatter.ISO_DATE_TIME);
		this.userType = UserType.parseId(JsonIO.parseInteger(jObject, "isCommercial"));
		this.seller = JsonIO.parseBoolean(jObject, "isSeller");
		this.companyName = JsonIO.parseString(jObject.get("name").getAsJsonObject(), "company");
		this.firstName = JsonIO.parseString(jObject.get("name").getAsJsonObject(), "firstName");
		this.lastName = JsonIO.parseString(jObject.get("name").getAsJsonObject(), "lastName");
		try {
			this.address = new Address(jObject.get("address").getAsJsonObject());
		} catch (NullPointerException e) {
			this.address = new Address(jObject.get("homeAddress").getAsJsonObject());
		}
		this.phoneNumber = JsonIO.parseString(jObject, "phone");
		this.emailAddress = JsonIO.parseString(jObject, "email");
		this.vat = JsonIO.parseString(jObject, "vat");
		this.riskGroup = RiskGroup.parseId(JsonIO.parseInteger(jObject, "riskGroup"));
		this.reputation = Reputation.parseId(JsonIO.parseInteger(jObject, "reputation"));
		this.expectedDeliveryTime = JsonIO.parseInteger(jObject, "shipsFast");
		this.amountSales = JsonIO.parseInteger(jObject, "sellCount");
		this.amountSoldItems = JsonIO.parseInteger(jObject, "soldItems");
		this.averageShippingTime = JsonIO.parseInteger(jObject, "avgShippingTime");
		this.onVacation = JsonIO.parseBoolean(jObject, "onVacation");
	}

	public User(String jsonString, int userId, String userName, LocalDateTime registrationDate, UserType userType,
			boolean seller, String companyName, String firstName, String lastName, Address address, String phoneNumber,
			String emailAddress, String vat, RiskGroup riskGroup, Reputation reputation, int expectedDeliveryTime,
			int amountSales, int amountSoldItems, int averageShippingTime, boolean onVacation) {
		this.jsonString = jsonString;
		this.userId = userId;
		this.userName = userName;
		this.registrationDate = registrationDate;
		this.userType = userType;
		this.seller = seller;
		this.companyName = companyName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.vat = vat;
		this.riskGroup = riskGroup;
		this.reputation = reputation;
		this.expectedDeliveryTime = expectedDeliveryTime;
		this.amountSales = amountSales;
		this.amountSoldItems = amountSoldItems;
		this.averageShippingTime = averageShippingTime;
		this.onVacation = onVacation;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (amountSales != other.amountSales)
			return false;
		if (amountSoldItems != other.amountSoldItems)
			return false;
		if (averageShippingTime != other.averageShippingTime)
			return false;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (expectedDeliveryTime != other.expectedDeliveryTime)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (onVacation != other.onVacation)
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (registrationDate == null) {
			if (other.registrationDate != null)
				return false;
		} else if (!registrationDate.equals(other.registrationDate))
			return false;
		if (reputation != other.reputation)
			return false;
		if (riskGroup != other.riskGroup)
			return false;
		if (seller != other.seller)
			return false;
		if (userId != other.userId)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userType != other.userType)
			return false;
		if (vat == null) {
			if (other.vat != null)
				return false;
		} else if (!vat.equals(other.vat))
			return false;
		return true;
	}

	public Address getAddress() {
		return address;
	}

	public int getAmountSales() {
		return amountSales;
	}

	public int getAmountSoldItems() {
		return amountSoldItems;
	}

	public int getAverageShippingTime() {
		return averageShippingTime;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public int getExpectedDeliveryTime() {
		return expectedDeliveryTime;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getJsonString() {
		return jsonString;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public Reputation getReputation() {
		return reputation;
	}

	public RiskGroup getRiskGroup() {
		return riskGroup;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public UserType getUserType() {
		return userType;
	}

	public String getVat() {
		return vat;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + amountSales;
		result = prime * result + amountSoldItems;
		result = prime * result + averageShippingTime;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + expectedDeliveryTime;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (onVacation ? 1231 : 1237);
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
		result = prime * result + ((reputation == null) ? 0 : reputation.hashCode());
		result = prime * result + ((riskGroup == null) ? 0 : riskGroup.hashCode());
		result = prime * result + (seller ? 1231 : 1237);
		result = prime * result + userId;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userType == null) ? 0 : userType.hashCode());
		result = prime * result + ((vat == null) ? 0 : vat.hashCode());
		return result;
	}

	public boolean isOnVacation() {
		return onVacation;
	}

	public boolean isSeller() {
		return seller;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setAmountSales(int amountSales) {
		this.amountSales = amountSales;
	}

	public void setAmountSoldItems(int amountSoldItems) {
		this.amountSoldItems = amountSoldItems;
	}

	public void setAverageShippingTime(int averageShippingTime) {
		this.averageShippingTime = averageShippingTime;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setExpectedDeliveryTime(int expectedDeliveryTime) {
		this.expectedDeliveryTime = expectedDeliveryTime;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setOnVacation(boolean onVacation) {
		this.onVacation = onVacation;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}

	public void setReputation(Reputation reputation) {
		this.reputation = reputation;
	}

	public void setRiskGroup(RiskGroup riskGroup) {
		this.riskGroup = riskGroup;
	}

	public void setSeller(boolean isSeller) {
		this.seller = isSeller;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	@Override
	public String toString() {
		return "User [jsonString=" + jsonString + ", userId=" + userId + ", userName=" + userName
				+ ", registrationDate=" + registrationDate + ", userType=" + userType + ", seller=" + seller
				+ ", companyName=" + companyName + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", phoneNumber=" + phoneNumber + ", emailAddress=" + emailAddress + ", vat=" + vat
				+ ", riskGroup=" + riskGroup + ", reputation=" + reputation + ", expectedDeliveryTime="
				+ expectedDeliveryTime + ", amountSales=" + amountSales + ", amountSoldItems=" + amountSoldItems
				+ ", averageShippingTime=" + averageShippingTime + ", onVacation=" + onVacation + "]";
	}

}
