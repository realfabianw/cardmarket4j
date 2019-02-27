package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;

import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonIO;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:ShippingMethod
 * @author QUE
 *
 */
public class ShippingMethod {
	private int shippingMethodId;
	private String name;
	private BigDecimal price;
	private boolean isLetter;
	private boolean isInsured;

	public ShippingMethod(int shippingMethodId, String name, BigDecimal price, boolean isLetter, boolean isInsured) {
		this.shippingMethodId = shippingMethodId;
		this.name = name;
		this.price = price;
		this.isLetter = isLetter;
		this.isInsured = isInsured;
	}

	public ShippingMethod(JsonObject jObject) {
		this.shippingMethodId = JsonIO.parseInteger(jObject, "idShippingMethod");
		this.name = JsonIO.parseString(jObject, "name");
		this.price = JsonIO.parseBigDecimal(jObject, "price");
		this.isLetter = JsonIO.parseBoolean(jObject, "isLetter");
		this.isInsured = JsonIO.parseBoolean(jObject, "isInsured");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingMethod other = (ShippingMethod) obj;
		if (isInsured != other.isInsured)
			return false;
		if (isLetter != other.isLetter)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (shippingMethodId != other.shippingMethodId)
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public int getShippingMethodId() {
		return shippingMethodId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isInsured ? 1231 : 1237);
		result = prime * result + (isLetter ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + shippingMethodId;
		return result;
	}

	public boolean isInsured() {
		return isInsured;
	}

	public boolean isLetter() {
		return isLetter;
	}

	public void setInsured(boolean isInsured) {
		this.isInsured = isInsured;
	}

	public void setLetter(boolean isLetter) {
		this.isLetter = isLetter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setShippingMethodId(int shippingMethodId) {
		this.shippingMethodId = shippingMethodId;
	}

	@Override
	public String toString() {
		return "ShippingMethod [shippingMethodId=" + shippingMethodId + ", "
				+ (name != null ? "name=" + name + ", " : "") + (price != null ? "price=" + price + ", " : "")
				+ "isLetter=" + isLetter + ", isInsured=" + isInsured + "]";
	}
}
