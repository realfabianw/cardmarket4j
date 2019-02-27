package de.ics.cardmarket4j;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.google.gson.JsonObject;

/**
 * Class that catches NullPointerExceptions while getting responses from the
 * Json-File.
 * 
 * @author QUE
 *
 */
public class JsonIO {
	public static BigDecimal parseBigDecimal(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBigDecimal();
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean parseBoolean(JsonObject jObject, String fieldName) {
		try {
			return jObject.get(fieldName).getAsBoolean();
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

	public static LocalDateTime parseLocalDateTime(JsonObject jObject, String fieldName, DateTimeFormatter dtf) {
		try {
			String ldt = parseString(jObject, fieldName);
			ldt = ldt.split("\\+0[0-9]")[0] + "+0" + ldt.split("\\+0")[1].charAt(0) + ":" + ldt.split("\\+0[0-9]")[1];
			return LocalDateTime.parse(ldt, dtf);
		} catch (Exception e) {
			return null;
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
