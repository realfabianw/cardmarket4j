package de.cardmarket4j.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.neovisionaries.i18n.LanguageCode;

import de.cardmarket4j.entity.enumeration.Condition;

/**
 * 
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Article
 * @author QUE
 *
 */
public class Article implements CardMarketArticle {
	private int articleId;
	private int productId;
	private LanguageCode languageCode;
	private String comment;
	private BigDecimal price;
	private int quantity;
	private boolean inShoppingCart;
	private Product product;
	private User seller;
	private LocalDateTime lastEdited;
	private Condition condition;
	private boolean foil;
	private boolean signed;
	private boolean altered;
	private boolean playset;
	private boolean firstEdition;

	public Article(int articleId, int productId, LanguageCode languageCode, String comment, BigDecimal price,
			int quantity, boolean inShoppingCart, Product product, User seller, LocalDateTime lastEdited,
			Condition condition, boolean foil, boolean signed, boolean altered, boolean playset, boolean firstEdition) {
		this.articleId = articleId;
		this.productId = productId;
		this.languageCode = languageCode;
		this.comment = comment;
		this.price = price;
		this.quantity = quantity;
		this.inShoppingCart = inShoppingCart;
		this.product = product;
		this.seller = seller;
		this.lastEdited = lastEdited;
		this.condition = condition;
		this.foil = foil;
		this.signed = signed;
		this.altered = altered;
		this.playset = playset;
		this.firstEdition = firstEdition;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (altered != other.altered)
			return false;
		if (articleId != other.articleId)
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (condition != other.condition)
			return false;
		if (firstEdition != other.firstEdition)
			return false;
		if (foil != other.foil)
			return false;
		if (inShoppingCart != other.inShoppingCart)
			return false;
		if (languageCode != other.languageCode)
			return false;
		if (lastEdited == null) {
			if (other.lastEdited != null)
				return false;
		} else if (!lastEdited.equals(other.lastEdited))
			return false;
		if (playset != other.playset)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (productId != other.productId)
			return false;
		if (quantity != other.quantity)
			return false;
		if (seller == null) {
			if (other.seller != null)
				return false;
		} else if (!seller.equals(other.seller))
			return false;
		if (signed != other.signed)
			return false;
		return true;
	}

	@Override
	public int getArticleId() {
		return articleId;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public Condition getCondition() {
		return condition;
	}

	@Override
	public LanguageCode getLanguageCode() {
		return languageCode;
	}

	public LocalDateTime getLastEdited() {
		return lastEdited;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	public Product getProduct() {
		return product;
	}

	@Override
	public int getProductId() {
		return productId;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	public User getSeller() {
		return seller;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (altered ? 1231 : 1237);
		result = prime * result + articleId;
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((condition == null) ? 0 : condition.hashCode());
		result = prime * result + (firstEdition ? 1231 : 1237);
		result = prime * result + (foil ? 1231 : 1237);
		result = prime * result + (inShoppingCart ? 1231 : 1237);
		result = prime * result + ((languageCode == null) ? 0 : languageCode.hashCode());
		result = prime * result + ((lastEdited == null) ? 0 : lastEdited.hashCode());
		result = prime * result + (playset ? 1231 : 1237);
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + productId;
		result = prime * result + quantity;
		result = prime * result + ((seller == null) ? 0 : seller.hashCode());
		result = prime * result + (signed ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean isAltered() {
		return altered;
	}

	@Override
	public boolean isFirstEdition() {
		return firstEdition;
	}

	@Override
	public boolean isFoil() {
		return foil;
	}

	public boolean isInShoppingCart() {
		return inShoppingCart;
	}

	@Override
	public boolean isPlayset() {
		return playset;
	}

	@Override
	public boolean isSigned() {
		return signed;
	}

	public void setAltered(boolean altered) {
		this.altered = altered;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public void setFirstEdition(boolean firstEdition) {
		this.firstEdition = firstEdition;
	}

	public void setFoil(boolean foil) {
		this.foil = foil;
	}

	public void setInShoppingCart(boolean inShoppingCart) {
		this.inShoppingCart = inShoppingCart;
	}

	public void setLanguageCode(LanguageCode languageCode) {
		this.languageCode = languageCode;
	}

	public void setLastEdited(LocalDateTime lastEdited) {
		this.lastEdited = lastEdited;
	}

	public void setPlayset(boolean playset) {
		this.playset = playset;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

	@Override
	public String toString() {
		return "Article [articleId=" + articleId + ", productId=" + productId + ", "
				+ (languageCode != null ? "languageCode=" + languageCode + ", " : "")
				+ (comment != null ? "comment=" + comment + ", " : "") + (price != null ? "price=" + price + ", " : "")
				+ "quantity=" + quantity + ", inShoppingCart=" + inShoppingCart + ", "
				+ (product != null ? "product=" + product + ", " : "")
				+ (seller != null ? "seller=" + seller + ", " : "")
				+ (lastEdited != null ? "lastEdited=" + lastEdited + ", " : "")
				+ (condition != null ? "condition=" + condition + ", " : "") + "foil=" + foil + ", signed=" + signed
				+ ", altered=" + altered + ", playset=" + playset + ", firstEdition=" + firstEdition + "]";
	}
}
