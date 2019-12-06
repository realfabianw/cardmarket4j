package de.ics.cardmarket4j.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import de.ics.cardmarket4j.entity.enumeration.OrderState;

/**
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Order
 * @author QUE
 *
 */
public class Order {
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
	private Integer mergedOrderId;
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

	public Order(int orderId, boolean isBuyer, User seller, User buyer, OrderState orderState, LocalDateTime dateBought,
			LocalDateTime datePaid, LocalDateTime dateSent, LocalDateTime dateReceived, LocalDateTime dateCanceled,
			String cancellationReason, Integer mergedOrderId, ShippingMethod shippingMethod, String trackingNumber,
			boolean isPresale, Address shippingAddress, int amountItems, String note, Evaluation evaluation,
			List<Article> listArticles, BigDecimal listArticlesValue, BigDecimal serviceFeeValue,
			BigDecimal totalValue) {
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
		if (mergedOrderId == null) {
			if (other.mergedOrderId != null)
				return false;
		} else if (!mergedOrderId.equals(other.mergedOrderId))
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

	public List<Article> getListArticles() {
		return listArticles;
	}

	public BigDecimal getListArticlesValue() {
		return listArticlesValue;
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
		result = prime * result + ((listArticles == null) ? 0 : listArticles.hashCode());
		result = prime * result + ((listArticlesValue == null) ? 0 : listArticlesValue.hashCode());
		result = prime * result + ((mergedOrderId == null) ? 0 : mergedOrderId.hashCode());
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

	public void setListArticles(List<Article> listArticles) {
		this.listArticles = listArticles;
	}

	public void setListArticlesValue(BigDecimal listArticlesValue) {
		this.listArticlesValue = listArticlesValue;
	}

	public void setMergedOrderId(Integer mergedOrderId) {
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
				+ (seller != null ? "seller=" + seller + ", " : "") + (buyer != null ? "buyer=" + buyer + ", " : "")
				+ (orderState != null ? "orderState=" + orderState + ", " : "")
				+ (dateBought != null ? "dateBought=" + dateBought + ", " : "")
				+ (datePaid != null ? "datePaid=" + datePaid + ", " : "")
				+ (dateSent != null ? "dateSent=" + dateSent + ", " : "")
				+ (dateReceived != null ? "dateReceived=" + dateReceived + ", " : "")
				+ (dateCanceled != null ? "dateCanceled=" + dateCanceled + ", " : "")
				+ (cancellationReason != null ? "cancellationReason=" + cancellationReason + ", " : "")
				+ (mergedOrderId != null ? "mergedOrderId=" + mergedOrderId + ", " : "")
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
