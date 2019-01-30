package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonHelper;
import de.ics.cardmarket4j.enums.OrderState;

/**
 * Order represents an order on cardmarket.
 * 
 * @author QUE
 * @version 30.01.2019
 *
 */
public class Order {
	private int orderId;
	private boolean isBuyer;
	private User buyer;
	private User seller;
	private OrderState orderState;
	private LocalDateTime dateBought;
	private LocalDateTime datePaid;
	private LocalDateTime dateSent;
	private LocalDateTime dateRecieved;
	private LocalDateTime dateCanceled;
	private String cancellationReason;
	private int mergedOrderId;
	private ShippingMethod shippingMethod;
	private String trackingNumber;
	private boolean isPresale;
	private Address shippingAddress;
	private int amountItems;
	private String note;
	private Evaluation evaluation;
	private List<Article> listArticles;
	private BigDecimal listArticlesValue;
	private BigDecimal serviceFeeValue;
	private BigDecimal totalValue;

	public Order(int orderId, boolean isBuyer, User buyer, User seller, OrderState orderState, LocalDateTime dateBought,
			LocalDateTime datePaid, LocalDateTime dateSent, LocalDateTime dateRecieved, LocalDateTime dateCanceled,
			String cancellationReason, int mergedOrderId, ShippingMethod shippingMethod, String trackingNumber,
			boolean isPresale, Address shippingAddress, int amountItems, String note, Evaluation evaluation,
			List<Article> listArticles, BigDecimal listArticlesValue, BigDecimal serviceFeeValue,
			BigDecimal totalValue) {
		this.orderId = orderId;
		this.isBuyer = isBuyer;
		this.buyer = buyer;
		this.seller = seller;
		this.orderState = orderState;
		this.dateBought = dateBought;
		this.datePaid = datePaid;
		this.dateSent = dateSent;
		this.dateRecieved = dateRecieved;
		this.dateCanceled = dateCanceled;
		this.cancellationReason = cancellationReason;
		this.mergedOrderId = mergedOrderId;
		this.shippingMethod = shippingMethod;
		this.trackingNumber = trackingNumber;
		this.isPresale = isPresale;
		this.shippingAddress = shippingAddress;
		this.amountItems = amountItems;
		this.note = note;
		this.evaluation = evaluation;
		this.listArticles = listArticles;
		this.listArticlesValue = listArticlesValue;
		this.serviceFeeValue = serviceFeeValue;
		this.totalValue = totalValue;
	}

	public Order(JsonObject jObject) {
		this.orderId = JsonHelper.parseInteger(jObject, "idOrder");
		this.isBuyer = JsonHelper.parseBoolean(jObject, "isBuyer");
		this.buyer = new User(jObject.get("buyer").getAsJsonObject());
		this.seller = new User(jObject.get("seller").getAsJsonObject());
		this.orderState = OrderState
				.parseValue(JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "state"));
		try {
			String date = JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "dateBought") == null
					? JsonHelper.parseString(jObject, "registerDate")
					: JsonHelper.parseString(jObject, "registrationDate");
			date = date.split("\\+0[0-9]")[0] + "+0" + date.split("\\+0")[1].charAt(0) + ":"
					+ date.split("\\+0[0-9]")[1];
			this.dateBought = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		} catch (Exception e) {

		}
		try {
			String date = JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "datePaid") == null
					? JsonHelper.parseString(jObject, "registerDate")
					: JsonHelper.parseString(jObject, "registrationDate");
			date = date.split("\\+0[0-9]")[0] + "+0" + date.split("\\+0")[1].charAt(0) + ":"
					+ date.split("\\+0[0-9]")[1];
			this.datePaid = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		} catch (Exception e) {

		}
		try {
			String date = JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "dateSent") == null
					? JsonHelper.parseString(jObject, "registerDate")
					: JsonHelper.parseString(jObject, "registrationDate");
			date = date.split("\\+0[0-9]")[0] + "+0" + date.split("\\+0")[1].charAt(0) + ":"
					+ date.split("\\+0[0-9]")[1];
			this.dateSent = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		} catch (Exception e) {

		}
		try {
			String date = JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "dateRecieved") == null
					? JsonHelper.parseString(jObject, "registerDate")
					: JsonHelper.parseString(jObject, "registrationDate");
			date = date.split("\\+0[0-9]")[0] + "+0" + date.split("\\+0")[1].charAt(0) + ":"
					+ date.split("\\+0[0-9]")[1];
			this.dateRecieved = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		} catch (Exception e) {

		}
		try {
			String date = JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "dateCanceled") == null
					? JsonHelper.parseString(jObject, "registerDate")
					: JsonHelper.parseString(jObject, "registrationDate");
			date = date.split("\\+0[0-9]")[0] + "+0" + date.split("\\+0")[1].charAt(0) + ":"
					+ date.split("\\+0[0-9]")[1];
			this.dateCanceled = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME);
		} catch (Exception e) {

		}
		this.cancellationReason = JsonHelper.parseString(jObject.get("state").getAsJsonObject(), "reason");
		this.mergedOrderId = JsonHelper.parseInteger(jObject.get("state").getAsJsonObject(), "wasMergedInto");
		this.shippingMethod = new ShippingMethod(jObject.get("shippingMethod").getAsJsonObject());
		this.trackingNumber = JsonHelper.parseString(jObject, "trackingNumber");
		this.isPresale = JsonHelper.parseBoolean(jObject, "isPresale");
		this.shippingAddress = new Address(jObject.get("shippingAddress").getAsJsonObject());
		this.amountItems = JsonHelper.parseInteger(jObject, "articleCount");
		this.note = JsonHelper.parseString(jObject, "note");
		try {
			this.evaluation = new Evaluation(jObject.get("evaluation").getAsJsonObject());
		} catch (NullPointerException e) {

		}
		this.listArticles = new ArrayList<>();
		for (JsonElement jEle : jObject.get("article").getAsJsonArray()) {
			listArticles.add(new Article(jEle.getAsJsonObject()));
		}
		this.listArticlesValue = JsonHelper.parseBigDecimal(jObject, "articleValue");
		this.serviceFeeValue = JsonHelper.parseBigDecimal(jObject, "serviceFeeValue");
		this.totalValue = JsonHelper.parseBigDecimal(jObject, "totalValue");

	}

	public int getAmountItems() {
		return amountItems;
	}

	public User getBuyer() {
		return buyer;
	}

	public String getCancellationReason() {
		return cancellationReason;
	}

	public LocalDateTime getDateBought() {
		return dateBought;
	}

	public LocalDateTime getDateCanceled() {
		return dateCanceled;
	}

	public LocalDateTime getDatePaid() {
		return datePaid;
	}

	public LocalDateTime getDateRecieved() {
		return dateRecieved;
	}

	public LocalDateTime getDateSent() {
		return dateSent;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public List<Article> getListArticles() {
		return listArticles;
	}

	public BigDecimal getListArticlesValue() {
		return listArticlesValue;
	}

	public int getMergedOrderId() {
		return mergedOrderId;
	}

	public String getNote() {
		return note;
	}

	public int getOrderId() {
		return orderId;
	}

	public OrderState getOrderState() {
		return orderState;
	}

	public User getSeller() {
		return seller;
	}

	public BigDecimal getServiceFeeValue() {
		return serviceFeeValue;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public ShippingMethod getShippingMethod() {
		return shippingMethod;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public boolean isBuyer() {
		return isBuyer;
	}

	public boolean isPresale() {
		return isPresale;
	}

	public void setAmountItems(int amountItems) {
		this.amountItems = amountItems;
	}

	public void setBuyer(boolean isBuyer) {
		this.isBuyer = isBuyer;
	}

	public void setBuyer(User buyer) {
		this.buyer = buyer;
	}

	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}

	public void setDateBought(LocalDateTime dateBought) {
		this.dateBought = dateBought;
	}

	public void setDateCanceled(LocalDateTime dateCanceled) {
		this.dateCanceled = dateCanceled;
	}

	public void setDatePaid(LocalDateTime datePaid) {
		this.datePaid = datePaid;
	}

	public void setDateRecieved(LocalDateTime dateRecieved) {
		this.dateRecieved = dateRecieved;
	}

	public void setDateSent(LocalDateTime dateSent) {
		this.dateSent = dateSent;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public void setListArticles(List<Article> listArticles) {
		this.listArticles = listArticles;
	}

	public void setListArticlesValue(BigDecimal listArticlesValue) {
		this.listArticlesValue = listArticlesValue;
	}

	public void setMergedOrderId(int mergedOrderId) {
		this.mergedOrderId = mergedOrderId;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setOrderState(OrderState orderState) {
		this.orderState = orderState;
	}

	public void setPresale(boolean isPresale) {
		this.isPresale = isPresale;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public void setServiceFeeValue(BigDecimal serviceFeeValue) {
		this.serviceFeeValue = serviceFeeValue;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public void setShippingMethod(ShippingMethod shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", isBuyer=" + isBuyer + ", "
				+ (buyer != null ? "buyer=" + buyer + ", " : "") + (seller != null ? "seller=" + seller + ", " : "")
				+ (orderState != null ? "orderState=" + orderState + ", " : "")
				+ (dateBought != null ? "dateBought=" + dateBought + ", " : "")
				+ (datePaid != null ? "datePaid=" + datePaid + ", " : "")
				+ (dateSent != null ? "dateSent=" + dateSent + ", " : "")
				+ (dateRecieved != null ? "dateRecieved=" + dateRecieved + ", " : "")
				+ (dateCanceled != null ? "dateCanceled=" + dateCanceled + ", " : "")
				+ (cancellationReason != null ? "cancellationReason=" + cancellationReason + ", " : "")
				+ "mergedOrderId=" + mergedOrderId + ", "
				+ (shippingMethod != null ? "shippingMethod=" + shippingMethod + ", " : "")
				+ (trackingNumber != null ? "trackingNumber=" + trackingNumber + ", " : "") + "isPresale=" + isPresale
				+ ", " + (shippingAddress != null ? "shippingAddress=" + shippingAddress + ", " : "") + "amountItems="
				+ amountItems + ", " + (note != null ? "note=" + note + ", " : "")
				+ (evaluation != null ? "evaluation=" + evaluation + ", " : "")
				+ (listArticles != null ? "listArticles=" + listArticles + ", " : "")
				+ (listArticlesValue != null ? "listArticlesValue=" + listArticlesValue + ", " : "")
				+ (serviceFeeValue != null ? "serviceFeeValue=" + serviceFeeValue + ", " : "")
				+ (totalValue != null ? "totalValue=" + totalValue : "") + "]";
	}

}
