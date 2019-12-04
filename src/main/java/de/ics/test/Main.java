package de.ics.test;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarketService;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException, InterruptedException {
		CardMarketService market = new CardMarketService("wnyJdqZJHmy2eQ28", "Jh7aj2jiR5REFMbeKn2VijlEpjwK1nf3",
				"YHEpS1HRHkIjixZFJAHfdqHVw3r3ZS7C", "VtADsKQR7OUTN77QP8IX2DkKetxo5kU9");

		LOGGER.info("Account: {}", market.getAccountService().getAccountInformation());
		LOGGER.info("Requests: {}/{}", market.getRequestCount(), market.getRequestLimit());
	}
}
