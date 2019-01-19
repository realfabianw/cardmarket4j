package de.ics.cardmarket4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	private static Logger LOGGER = LoggerFactory.getLogger("Main");

	public static void main(String[] args) {
		CardMarket market = new CardMarket("Cr0yANU52r7iDhlQ", "EWIRcYdR7pnHWdLys8uJQr3aPgLZFXgx",
				"SnR9w5ZMT0kQwyfT2ahjqNZr7I0vKJcl", "bajBeBYsv5B4O1UA1MjGOG3AaZiAJpWM");
		market.setVacationStatus(false);
	}
}
