package de.ics.cardmarket4j.structs;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.ics.cardmarket4j.JsonIO;
import de.ics.cardmarket4j.enums.OrderState;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Order
 * @author QUE
 *
 */
public class Order {
	private String jsonString;
	private int orderId;
	private boolean isBuyer;
	private User seller;
	private User buyer;
	private OrderState orderState;
	private LocalDateTime dateBought;
	private LocalDateTime datePaid;
	private LocalDateTime dateSent;
	private LocalDateTime dateReceived;
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

	public Order(JsonObject jObject) {
		this.jsonString = jObject.toString();
		this.orderId = JsonIO.parseInteger(jObject, "idOrder");
		this.isBuyer = JsonIO.parseBoolean(jObject, "isBuyer");
		this.buyer = new User(jObject.get("buyer").getAsJsonObject());
		this.seller = new User(jObject.get("seller").getAsJsonObject());

		JsonObject jState = jObject.get("state").getAsJsonObject();
		this.orderState = OrderState.parseValue(JsonIO.parseString(jState, "state"));
		this.dateBought = JsonIO.parseLocalDateTime(jState, "dateBought", DateTimeFormatter.ISO_DATE_TIME);
		this.datePaid = JsonIO.parseLocalDateTime(jState, "datePaid", DateTimeFormatter.ISO_DATE_TIME);
		this.dateSent = JsonIO.parseLocalDateTime(jState, "dateSent", DateTimeFormatter.ISO_DATE_TIME);
		this.dateReceived = JsonIO.parseLocalDateTime(jState, "dateReceived", DateTimeFormatter.ISO_DATE_TIME);
		this.dateCanceled = JsonIO.parseLocalDateTime(jState, "dateCanceled", DateTimeFormatter.ISO_DATE_TIME);
		this.cancellationReason = JsonIO.parseString(jState, "reason");
		this.mergedOrderId = JsonIO.parseInteger(jState, "wasMergedInto");

		this.shippingMethod = new ShippingMethod(jObject.get("shippingMethod").getAsJsonObject());
		this.trackingNumber = JsonIO.parseString(jObject, "trackingNumber");
		this.isPresale = JsonIO.parseBoolean(jObject, "isPresale");
		this.shippingAddress = new Address(jObject.get("shippingAddress").getAsJsonObject());
		this.amountItems = JsonIO.parseInteger(jObject, "articleCount");
		this.note = JsonIO.parseString(jObject, "note");
		try {
			this.evaluation = new Evaluation(jObject.get("evaluation").getAsJsonObject());
		} catch (NullPointerException e) {

		}
		this.listArticles = new ArrayList<>();
		for (JsonElement jEle : jObject.get("article").getAsJsonArray()) {
			listArticles.add(new Article(jEle.getAsJsonObject()));
		}
		this.listArticlesValue = JsonIO.parseBigDecimal(jObject, "articleValue");
		this.serviceFeeValue = JsonIO.parseBigDecimal(jObject, "serviceFeeValue");
		this.totalValue = JsonIO.parseBigDecimal(jObject, "totalValue");

	}

	public Order(String jsonString, int orderId, boolean isBuyer, User seller, User buyer, OrderState orderState,
			LocalDateTime dateBought, LocalDateTime datePaid, LocalDateTime dateSent, LocalDateTime dateReceived,
			LocalDateTime dateCanceled, String cancellationReason, int mergedOrderId, ShippingMethod shippingMethod,
			String trackingNumber, boolean isPresale, Address shippingAddress, int amountItems, String note,
			Evaluation evaluation, List<Article> listArticles, BigDecimal listArticlesValue, BigDecimal serviceFeeValue,
			BigDecimal totalValue) {
		this.jsonString = jsonString;
		this.orderId = orderId;
		this.isBuyer = isBuyer;
		this.seller = seller;
		this.buyer = buyer;
		this.orderState = orderState;
		this.dateBought = dateBought;
		this.datePaid = datePaid;
		this.dateSent = dateSent;
		this.dateReceived = dateReceived;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (amountItems != other.amountItems)
			return false;
		if (buyer == null) {
			if (other.buyer != null)
				return false;
		} else if (!buyer.equals(other.buyer))
			return false;
		if (cancellationReason == null) {
			if (other.cancellationReason != null)
				return false;
		} else if (!cancellationReason.equals(other.cancellationReason))
			return false;
		if (dateBought == null) {
			if (other.dateBought != null)
				return false;
		} else if (!dateBought.equals(other.dateBought))
			return false;
		if (dateCanceled == null) {
			if (other.dateCanceled != null)
				return false;
		} else if (!dateCanceled.equals(other.dateCanceled))
			return false;
		if (datePaid == null) {
			if (other.datePaid != null)
				return false;
		} else if (!datePaid.equals(other.datePaid))
			return false;
		if (dateReceived == null) {
			if (other.dateReceived != null)
				return false;
		} else if (!dateReceived.equals(other.dateReceived))
			return false;
		if (dateSent == null) {
			if (other.dateSent != null)
				return false;
		} else if (!dateSent.equals(other.dateSent))
			return false;
		if (evaluation == null) {
			if (other.evaluation != null)
				return false;
		} else if (!evaluation.equals(other.evaluation))
			return false;
		if (isBuyer != other.isBuyer)
			return false;
		if (isPresale != other.isPresale)
			return false;
		if (jsonString == null) {
			if (other.jsonString != null)
				return false;
		} else if (!jsonString.equals(other.jsonString))
			return false;
		if (listArticles == null) {
			if (other.listArticles != null)
				return false;
		} else if (!listArticles.equals(other.listArticles))
			return false;
		if (listArticlesValue == null) {
			if (other.listArticlesValue != null)
				return false;
		} else if (!listArticlesValue.equals(other.listArticlesValue))
			return false;
		if (mergedOrderId != other.mergedOrderId)
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (orderId != other.orderId)
			return false;
		if (orderState != other.orderState)
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (serviceFeeValue == null) {
			if (other.serviceFeeValue != null)
				return false;
		} else if (!serviceFeeValue.equals(other.serviceFeeValue))
			return false;
		if (shippingAddress == null) {
			if (other.shippingAddress != null)
				return false;
		} else if (!shippingAddress.equals(other.shippingAddress))
			return false;
		if (shippingMethod == null) {
			if (other.shippingMethod != null)
				return false;
		} else if (!shippingMethod.equals(other.shippingMethod))
			return false;
		if (totalValue == null) {
			if (other.totalValue != null)
				return false;
		} else if (!totalValue.equals(other.totalValue))
			return false;
		if (trackingNumber == null) {
			if (other.trackingNumber != null)
				return false;
		} else if (!trackingNumber.equals(other.trackingNumber))
			return false;
		return true;
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

	public LocalDateTime getDateReceived() {
		return dateReceived;
	}

	public LocalDateTime getDateSent() {
		return dateSent;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public String getJsonString() {
		return jsonString;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amountItems;
		result = prime * result + ((buyer == null) ? 0 : buyer.hashCode());
		result = prime * result + ((cancellationReason == null) ? 0 : cancellationReason.hashCode());
		result = prime * result + ((dateBought == null) ? 0 : dateBought.hashCode());
		result = prime * result + ((dateCanceled == null) ? 0 : dateCanceled.hashCode());
		result = prime * result + ((datePaid == null) ? 0 : datePaid.hashCode());
		result = prime * result + ((dateReceived == null) ? 0 : dateReceived.hashCode());
		result = prime * result + ((dateSent == null) ? 0 : dateSent.hashCode());
		result = prime * result + ((evaluation == null) ? 0 : evaluation.hashCode());
		result = prime * result + (isBuyer ? 1231 : 1237);
		result = prime * result + (isPresale ? 1231 : 1237);
		result = prime * result + ((jsonString == null) ? 0 : jsonString.hashCode());
		result = prime * result + ((listArticles == null) ? 0 : listArticles.hashCode());
		result = prime * result + ((listArticlesValue == null) ? 0 : listArticlesValue.hashCode());
		result = prime * result + mergedOrderId;
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + orderId;
		result = prime * result + ((orderState == null) ? 0 : orderState.hashCode());
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + ((serviceFeeValue == null) ? 0 : serviceFeeValue.hashCode());
		result = prime * result + ((shippingAddress == null) ? 0 : shippingAddress.hashCode());
		result = prime * result + ((shippingMethod == null) ? 0 : shippingMethod.hashCode());
		result = prime * result + ((totalValue == null) ? 0 : totalValue.hashCode());
		result = prime * result + ((trackingNumber == null) ? 0 : trackingNumber.hashCode());
		return result;
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

	public void setDateReceived(LocalDateTime dateReceived) {
		this.dateReceived = dateReceived;
	}

	public void setDateSent(LocalDateTime dateSent) {
		this.dateSent = dateSent;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
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
		return "Order [jsonString=" + jsonString + ", orderId=" + orderId + ", isBuyer=" + isBuyer + ", seller="
				+ seller + ", buyer=" + buyer + ", orderState=" + orderState + ", dateBought=" + dateBought
				+ ", datePaid=" + datePaid + ", dateSent=" + dateSent + ", dateReceived=" + dateReceived
				+ ", dateCanceled=" + dateCanceled + ", cancellationReason=" + cancellationReason + ", mergedOrderId="
				+ mergedOrderId + ", shippingMethod=" + shippingMethod + ", trackingNumber=" + trackingNumber
				+ ", isPresale=" + isPresale + ", shippingAddress=" + shippingAddress + ", amountItems=" + amountItems
				+ ", note=" + note + ", evaluation=" + evaluation + ", listArticles=" + listArticles
				+ ", listArticlesValue=" + listArticlesValue + ", serviceFeeValue=" + serviceFeeValue + ", totalValue="
				+ totalValue + "]";
	}
}
