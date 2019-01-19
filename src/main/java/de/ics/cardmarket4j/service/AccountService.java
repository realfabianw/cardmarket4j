package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.CardMarket;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.structs.Account;

public class AccountService implements CardMarketService {
	private final CardMarket cardMarket;

	@Override
	public CardMarket getCardMarket() {
		return cardMarket;
	}

	public AccountService(CardMarket cardMarket) {
		this.cardMarket = cardMarket;
	}

	
	public Account getAccountInformation() {
		try {
			return new Account(request("account", HTTPMethod.GET).getValue1());
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			return null;
		}
	}

	public boolean setVacationStatus(boolean vacation) {
		String vacationParameter = vacation == true ? "true" : "false";
		try {
			Pair<Integer, JsonElement> result = request("account/vacation?onVacation=" + vacationParameter,
					HTTPMethod.PUT);
			return true;
		} catch (InvalidKeyException | NoSuchAlgorithmException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
}
