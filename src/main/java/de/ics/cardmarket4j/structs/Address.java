package de.ics.cardmarket4j.structs;

import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

import de.ics.cardmarket4j.JsonHelper;

/**
 * Class that represents an address, either of the own account or an another
 * user.
 * 
 * This class should be complete and not require further additions.
 * 
 * @author QUE
 * @version 29.01.2018
 *
 */
public class Address {
	private String name;
	private String extra;
	private String street;
	private String zip;
	private String city;
	private CountryCode country;

	public Address(JsonObject jObject) {
		this.name = JsonHelper.parseString(jObject, "name");
		this.extra = JsonHelper.parseString(jObject, "extra");
		this.street = JsonHelper.parseString(jObject, "street");
		this.zip = JsonHelper.parseString(jObject, "zip");
		this.city = JsonHelper.parseString(jObject, "city");
		if (!JsonHelper.parseString(jObject, "country").equals("D")) {
			this.country = CountryCode.getByCode(JsonHelper.parseString(jObject, "country"));
		} else {
			this.country = CountryCode.DE;
		}
	}

	public Address(String name, String extra, String street, String zip, String city, CountryCode country) {
		this.name = name;
		this.extra = extra;
		this.street = street;
		this.zip = zip;
		this.city = city;
		this.country = country;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country != other.country)
			return false;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		if (zip == null) {
			if (other.zip != null)
				return false;
		} else if (!zip.equals(other.zip))
			return false;
		return true;
	}

	public String getCity() {
		return city;
	}

	public CountryCode getCountry() {
		return country;
	}

	public String getExtra() {
		return extra;
	}

	public String getName() {
		return name;
	}

	public String getStreet() {
		return street;
	}

	public String getZip() {
		return zip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		return result;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(CountryCode country) {
		this.country = country;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [" + (name != null ? "name=" + name + ", " : "")
				+ (extra != null ? "extra=" + extra + ", " : "") + (street != null ? "street=" + street + ", " : "")
				+ (zip != null ? "zip=" + zip + ", " : "") + (city != null ? "city=" + city + ", " : "")
				+ (country != null ? "country=" + country : "") + "]";
	}
}
