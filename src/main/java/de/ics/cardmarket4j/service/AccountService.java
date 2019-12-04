package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.entity.Account;
import de.ics.cardmarket4j.entity.Conversation;
import de.ics.cardmarket4j.enums.HTTPMethod;

/**
 * AccountService provides a connection to several account related functions.
 * TODO post and delete messages
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Management
 * @author QUE
 * @version 0.7
 *
 */
public class AccountService extends AbstractService {
	public AccountService(CardMarketService cardMarket) {
		super(cardMarket);

	}

	/**
	 * Returns an Account instance
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account
	 * @return {@code Account account}
	 * @throws IOException
	 * @version 0.7
	 */
	public Account getAccount() throws IOException {
		JsonElement response = request("account", HTTPMethod.GET);
		return JsonIO.getGson().fromJson(response.getAsJsonObject().get("account"), Account.class);
	}

	/**
	 * Returns a conversation overview. Displays all conversation partners, but not
	 * every message sent.
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Messages
	 * @return {@code List<Conversation> listConversation}
	 * @throws IOException
	 * @version 0.7
	 */
	public List<Conversation> getMessages() throws IOException {
		List<Conversation> listConversations = new ArrayList<>();
		JsonElement response = request("account/messages", HTTPMethod.GET);
		for (JsonElement jElement : response.getAsJsonObject().get("thread").getAsJsonArray()) {
			listConversations.add(JsonIO.getGson().fromJson(jElement, Conversation.class));
		}
		return listConversations;
	}

	/**
	 * Returns the whole conversation with the user based on the given userId.
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Messages
	 * @return {@code Conversation conversation}
	 * @throws IOException
	 * @version 0.7
	 */
	public Conversation getMessages(int userId) throws IOException {
		JsonElement response = request("account/messages/" + userId, HTTPMethod.GET);
		return JsonIO.getGson().fromJson(response, Conversation.class);
	}

	/**
	 * Changes the accounts vacation status based on the given parameter
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Vacation
	 * @param vacation
	 * @return {@code boolean success}
	 * @throws IOException
	 * @version 0.7
	 */
	public boolean setVacationStatus(boolean vacation) throws IOException {
		String vacationParameter = vacation == true ? "true" : "false";
		JsonElement response = request("account/vacation?onVacation=" + vacationParameter, HTTPMethod.PUT);
		if (getLastResponse().getValue0() == 200) {
			return true;
		} else {
			return false;
		}
	}
}
