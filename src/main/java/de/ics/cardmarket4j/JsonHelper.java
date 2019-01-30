package de.ics.cardmarket4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Class that catches NullPointerExceptions while getting responses from the
 * Json-File.
 * 
 * @author QUE
 *
 */
public class JsonHelper {
	public static BigDecimal parseBigDecimal(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBigDecimal();
		} catch (Exception e) {
			return BigDecimal.ZERO;
		}
	}

	public static boolean parseBoolean(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBoolean();
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean parseBooleanFromString(JsonObject jObject, String fieldName, String trueString,
			String falseString) {
		try {
			String returnedString = jObject.get(fieldName).getAsString();
			if (returnedString.equals(trueString)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	public static double parseDouble(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsDouble();
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseInteger(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsInt();
		} catch (Exception e) {
			return 0;
		}
	}

	public static List<String> parseListString(JsonObject jObject, String arrayName) {
		List<String> listString = new ArrayList<>();
		try {
			JsonArray jArray = jObject.get(arrayName).getAsJsonArray();
			for (JsonElement jElement : jArray) {
				listString.add(jElement.getAsString());
			}
			return listString;
		} catch (Exception e) {
			return new ArrayList<String>();
		}
	}

	public static LocalDateTime parseLocalDateTime(JsonObject jObject, String fieldName, DateTimeFormatter dtf) {
		try {
			return LocalDateTime.parse(jObject.get(fieldName).getAsString(), dtf);
		} catch (Exception e) {
			return LocalDateTime.now();
		}
	}

	public static String parseString(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsString();
		} catch (Exception e) {
			return null;
		}
	}

}
