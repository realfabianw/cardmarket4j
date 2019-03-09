package de.ics.cardmarket4j.structs;

import com.google.gson.JsonObject;
import com.neovisionaries.i18n.CountryCode;

import de.ics.cardmarket4j.JsonIO;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Address
 * @author QUE
 *
 */
public class Address {
	private String jsonString;
	private String name;
	private String extra;
	private String street;
	private String zip;
	private String city;
	private CountryCode country;

	public Address(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.name = JsonIO.parseString(jObject, "name");
		this.extra = JsonIO.parseString(jObject, "extra");
		this.street = JsonIO.parseString(jObject, "street");
		this.zip = JsonIO.parseString(jObject, "zip");
		this.city = JsonIO.parseString(jObject, "city");
		if (!JsonIO.parseString(jObject, "country").equals("D")) {
			this.country = CountryCode.getByCode(JsonIO.parseString(jObject, "country"));
		} else {
			this.country = CountryCode.DE;
		}
	}

	public Address(String jsonString, String name, String extra, String street, String zip, String city,
			CountryCode country) {
		this.jsonString = jsonString;
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
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
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

	public String getJsonString() {
		return jsonString;
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
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
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

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
		return "Address [jsonString=" + jsonString + ", name=" + name + ", extra=" + extra + ", street=" + street
				+ ", zip=" + zip + ", city=" + city + ", country=" + country + "]";
	}
}
