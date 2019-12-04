package de.ics.cardmarket4j.entity.deserializer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import de.ics.cardmarket4j.entity.Conversation;
import de.ics.cardmarket4j.entity.Message;
import de.ics.cardmarket4j.entity.User;
import de.ics.cardmarket4j.utils.JsonIO;

public class ConversationDeserializer implements JsonDeserializer<Conversation> {

	@Override
	public Conversation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		JsonObject jObject = json.getAsJsonObject();

		User partner = JsonIO.getGson().fromJson(jObject.get("partner"), User.class);
		List<Message> listMessages = new ArrayList<>();
		if (jObject.get("message").isJsonArray()) {
			for (JsonElement jEle : jObject.get("message").getAsJsonArray()) {
				listMessages.add(JsonIO.getGson().fromJson(jEle, Message.class));
			}
		} else {
			listMessages.add(JsonIO.getGson().fromJson(jObject.get("message"), Message.class));
		}
		int unreadMessages = JsonIO.parseInteger(jObject, "unreadMessages");

		return new Conversation(partner, listMessages, unreadMessages);
	}

}
