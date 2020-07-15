package de.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.cardmarket4j.entity.Message;
import de.cardmarket4j.util.JsonIO;

public class MessageDeserializer implements JsonDeserializer<Message> {

	@Override
	public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		int messageId = JsonIO.parseInteger(jObject, "idMessage");
		boolean sending = JsonIO.parseBoolean(jObject, "isSending");
		boolean unread = JsonIO.parseBoolean(jObject, "unread");
		String dateString = JsonIO.parseString(jObject, "date");
		dateString = dateString.split("\\+0[0-9]")[0] + "+0" + dateString.split("\\+0")[1].charAt(0) + ":"
				+ dateString.split("\\+0[0-9]")[1];
		LocalDateTime date = LocalDateTime.parse(dateString, DateTimeFormatter.ISO_DATE_TIME);
		String text = JsonIO.parseString(jObject, "text");

		return new Message(messageId, sending, unread, date, text);
	}

}
