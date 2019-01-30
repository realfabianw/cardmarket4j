package de.ics.cardmarket4j.structs;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;

/**
 * Conversation represents a "Message-Thread" on cardmarket.
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Messages
 * @author QUE
 * @version 30.01.2019
 */
public class Conversation {
	private User partner;
	private List<Message> listMessages;
	private int unreadMessages;

	public Conversation(JsonObject jObject) {
		this.partner = new User(jObject.get("partner").getAsJsonObject());
		this.listMessages = new ArrayList<>();
		try {
			// Message Overview (Single Message)
			listMessages.add(new Message(jObject.get("message").getAsJsonObject()));
		} catch (IllegalStateException e) {
			// Multiple Messages
			for (JsonElement jEle : jObject.get("message").getAsJsonArray()) {
				listMessages.add(new Message(jEle.getAsJsonObject()));
			}
		}
		this.unreadMessages = JsonHelper.parseInteger(jObject, "unreadMessages");
	}

	public List<Message> getListMessages() {
		return listMessages;
	}

	public User getPartner() {
		return partner;
	}

	public int getUnreadMessages() {
		return unreadMessages;
	}

	public void setListMessages(List<Message> listMessages) {
		this.listMessages = listMessages;
	}

	public void setPartner(User partner) {
		this.partner = partner;
	}

	public void setUnreadMessages(int unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	@Override
	public String toString() {
		return "Conversation [" + (partner != null ? "partner=" + partner + ", " : "")
				+ (listMessages != null ? "listMessages=" + listMessages + ", " : "") + "unreadMessages="
				+ unreadMessages + "]";
	}
}
