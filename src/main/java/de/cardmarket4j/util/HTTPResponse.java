package de.cardmarket4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class HTTPResponse {
	private static Logger LOGGER = LoggerFactory.getLogger("HTTPResponse");
	private final int responseCode;
	private final String responseString;
	private final JsonElement responseJsonElement;

	public HTTPResponse(int responseCode, String responseString) {
		this.responseCode = responseCode;
		this.responseString = responseString;
		LOGGER.trace("Response Body:\t{}", responseString);
		this.responseJsonElement = JsonParser.parseString(responseString);
		LOGGER.debug("Response:\t{} {}", responseCode, responseJsonElement);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HTTPResponse other = (HTTPResponse) obj;
		if (responseCode != other.responseCode)
			return false;
		if (responseString == null) {
			if (other.responseString != null)
				return false;
		} else if (!responseString.equals(other.responseString))
			return false;
		return true;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public JsonElement getResponseJsonElement() {
		return responseJsonElement;
	}

	public String getResponseString() {
		return responseString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + responseCode;
		result = prime * result + ((responseString == null) ? 0 : responseString.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "HTTPResponse [responseCode=" + responseCode + ", "
				+ (responseString != null ? "responseString=" + responseString + ", " : "")
				+ (responseJsonElement != null ? "responseJsonElement=" + responseJsonElement : "") + "]";
	}
}
