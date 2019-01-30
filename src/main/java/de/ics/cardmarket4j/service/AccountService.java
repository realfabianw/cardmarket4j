package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Account;
import de.ics.cardmarket4j.structs.Conversation;

/**
 * AccountService provides a connection to several account related functions.
 * TODO post and delete messages
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Management
 * @author QUE
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
	 */
	public Account getAccountInformation() throws IOException {
		return new Account(
				request("account", HTTPMethod.GET).getValue1().getAsJsonObject().get("account").getAsJsonObject());
	}

	/**
	 * Returns a conversation overview. Displays all conversation partners, but not
	 * every message sent.
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Messages
	 * @return {@code List<Conversation> listConversation}
	 * @throws IOException
	 */
	public List<Conversation> getMessages() throws IOException {
		List<Conversation> listConversations = new ArrayList<>();
		Pair<Integer, JsonElement> response = request("account/messages", HTTPMethod.GET);
		for (JsonElement jElement : response.getValue1().getAsJsonObject().get("thread").getAsJsonArray()) {
			listConversations.add(new Conversation(jElement.getAsJsonObject()));
		}
		return listConversations;
	}

	/**
	 * Returns the whole conversation with the user based on the given userId.
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Messages
	 * @return {@code Conversation conversation}
	 * @throws IOException
	 */
	public Conversation getMessages(int userId) throws IOException {
		List<Conversation> listConversations = new ArrayList<>();
		Pair<Integer, JsonElement> response = request("account/messages/" + userId, HTTPMethod.GET);

		return new Conversation(response.getValue1().getAsJsonObject());
	}

	/**
	 * Changes the accounts vacation status based on the given parameter
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Account_Vacation
	 * @param vacation
	 * @return {@code boolean success}
	 * @throws IOException
	 */
	public boolean setVacationStatus(boolean vacation) throws IOException {
		String vacationParameter = vacation == true ? "true" : "false";
		Pair<Integer, JsonElement> response = request("account/vacation?onVacation=" + vacationParameter,
				HTTPMethod.PUT);
		if (response.getValue0() == 200) {
			return true;
		} else {
			return false;
		}
	}
}
