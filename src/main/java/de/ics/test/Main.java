package de.ics.test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.ics.cardmarket4j.CardMarket;
import de.ics.cardmarket4j.enums.Condition;
import de.ics.cardmarket4j.enums.Language;
import de.ics.cardmarket4j.structs.Article;
import de.ics.cardmarket4j.structs.Product;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) throws IOException {
		CardMarket market = new CardMarket("Cr0yANU52r7iDhlQ", "EWIRcYdR7pnHWdLys8uJQr3aPgLZFXgx",
				"SnR9w5ZMT0kQwyfT2ahjqNZr7I0vKJcl", "bajBeBYsv5B4O1UA1MjGOG3AaZiAJpWM");

		LOGGER.info(market.getStockService().getStock().toString());
	}
}
