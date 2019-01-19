package de.ics.cardmarket4j.service;

import java.io.IOException;
import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarket;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Account;

public class AccountService extends AbstractService {
	public AccountService(CardMarket cardMarket) {
		super(cardMarket);

	}

	public Account getAccountInformation() throws IOException {
		return new Account(request("account", HTTPMethod.GET).getValue1());
	}

	/**
	 * TODO Gibt offensichtlich gesamte Userprofile im Verbund mit den Nachrichten
	 * zurück. User müssen zuerst implementiert werden.
	 * 
	 * @throws IOException
	 */
	public void getMessages() throws IOException {
		Pair<Integer, JsonElement> response = request("account/messages", HTTPMethod.GET);
	}

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
