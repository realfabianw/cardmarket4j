package de.ics.cardmarket4j.structs;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.entity.User;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Messages
 * @author QUE
 * 
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
		this.unreadMessages = JsonIO.parseInteger(jObject, "unreadMessages");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversation other = (Conversation) obj;
		if (listMessages == null) {
			if (other.listMessages != null)
				return false;
		} else if (!listMessages.equals(other.listMessages))
			return false;
		if (partner == null) {
			if (other.partner != null)
				return false;
		} else if (!partner.equals(other.partner))
			return false;
		if (unreadMessages != other.unreadMessages)
			return false;
		return true;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listMessages == null) ? 0 : listMessages.hashCode());
		result = prime * result + ((partner == null) ? 0 : partner.hashCode());
		result = prime * result + unreadMessages;
		return result;
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
