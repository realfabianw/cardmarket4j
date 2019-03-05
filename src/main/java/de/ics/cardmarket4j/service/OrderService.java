package de.ics.cardmarket4j.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import com.google.gson.JsonElement;

import de.ics.cardmarket4j.AbstractService;
import de.ics.cardmarket4j.CardMarketService;
import de.ics.cardmarket4j.enums.HTTPMethod;
import de.ics.cardmarket4j.enums.OrderState;
import de.ics.cardmarket4j.enums.OrderType;
import de.ics.cardmarket4j.structs.Order;

/**
 * OrderServices provides a connection to several order related functions on
 * cardmarket. TODO Add (Orders, GET/PUT)
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Order_Management
 * @author QUE
 *
 */
public class OrderService extends AbstractService {
	public OrderService(CardMarketService cardMarket) {
		super(cardMarket);
	}

	/**
	 * Returns all orders that match the given parameters.
	 * 
	 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Filter_Orders
	 * @param orderType
	 * @param orderState
	 * @return {@code List<Order> listOrders}
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public List<Order> getOrders(OrderType orderType, OrderState orderState) throws IOException, InterruptedException {
		List<Order> listOrders = new ArrayList<>();
		int index = 1;
		int responseCode = 200;
		while (responseCode == 200 || responseCode == 206) {
			Pair<Integer, JsonElement> response = request(
					"orders/" + orderType.getDisplayValue() + "/" + orderState.getDisplayValue() + "/" + index,
					HTTPMethod.GET);
			responseCode = response.getValue0();
			index += 100;
			if (responseCode == 200 || responseCode == 206) {
				for (JsonElement jEle : response.getValue1().getAsJsonObject().get("order").getAsJsonArray()) {
					listOrders.add(new Order(jEle.getAsJsonObject()));
				}
			}
			Thread.sleep(100);
		}
		return listOrders;
	}
}
