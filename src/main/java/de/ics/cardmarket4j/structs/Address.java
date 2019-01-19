package de.ics.cardmarket4j.structs;

import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

public class Address {
	private String name;
	private String extra;
	private String street;
	private String zip;
	private String city;
	private CountryCode country;

	public Address(JsonObject jAccount) {
		JsonObject j = jAccount.get("homeAddress").getAsJsonObject();
		this.name = j.get("name").getAsString();
		try {
			this.extra = j.get("extra").getAsString();
		} catch (UnsupportedOperationException e) {
			this.extra = "";
		}
		this.street = j.get("street").getAsString();
		this.zip = j.get("zip").getAsString();
		this.city = j.get("city").getAsString();
		if (!j.get("country").getAsString().equals("D")) {
			this.country = CountryCode.getByCode(j.get("country").getAsString());
		} else {
			this.country = CountryCode.DE;
		}
	}

	@Override
	public String toString() {
		return "Address [name=" + name + ", extra=" + extra + ", street=" + street + ", zip=" + zip + ", city=" + city
				+ ", country=" + country + "]";
	}
}
