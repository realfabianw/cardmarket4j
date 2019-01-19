package de.ics.cardmarket4j.service;

import java.io.IOException;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarket;
import de.ics.cardmarket4j.enums.HTTPMethod;

public class MarketplaceService extends AbstractService {

	public MarketplaceService(CardMarket cardMarket) {
		super(cardMarket);
		// TODO Auto-generated constructor stub
	}

	public void getSupportedGames() throws IOException {
		request("games", HTTPMethod.GET);
	}

}
