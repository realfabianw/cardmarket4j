package de.cardmarket4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	private static final Logger LOGGER = LoggerFactory.getLogger("Test");

	public static void main(String[] args) throws IOException {
		CardMarketService cardMarketService = new CardMarketService("SgM0KpesvabMCz8q",
				"TDwfy4yj3HAniBwsYyPcQEgHc0YL0Cgb", "OzbPvdcDJmr4IXYNSPLavmnjQm19SwVO",
				"liWmJqYiMEl9q3QWoDZBNMpKyUCRsL6A");

		cardMarketService.getAccountService().getMessages();

	}

}
