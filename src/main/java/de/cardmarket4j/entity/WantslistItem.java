package de.cardmarket4j.entity;

import de.cardmarket4j.entity.enumeration.Condition;
import de.cardmarket4j.entity.enumeration.ItemType;
import de.cardmarket4j.entity.enumeration.Language;

import java.util.List;
import java.util.Objects;

/**
 *
 * @see https://www.mkmapi.eu/ws/documentation/API_2.0:Entities:Wantslist_Item
 * @author Hangovers
 *
 */
public class WantslistItem {

    private int idWant;
    private int count;
    private long wishPrice;
    private long fromPrice;
    private boolean mailAlert;
    private ItemType type;
    private int idProduct;
    private Product product;
    private List<Language> idLanguage;
    private Condition minCondition;
    private boolean isFoil;
    private boolean isSigned;
    private boolean isAltered;
    private boolean isFirstEd;

    public WantslistItem(int idWant, int count, long wishPrice, long fromPrice, boolean mailAlert, ItemType type, int idProduct, Product product, List<Language> idLanguage, Condition minCondition, boolean isFoil, boolean isSigned, boolean isAltered, boolean isFirstEd) {
        this.idWant = idWant;
        this.count = count;
        this.wishPrice = wishPrice;
        this.fromPrice = fromPrice;
        this.mailAlert = mailAlert;
        this.type = type;
        this.idProduct = idProduct;
        this.product = product;
        this.idLanguage = idLanguage;
        this.minCondition = minCondition;
        this.isFoil = isFoil;
        this.isSigned = isSigned;
        this.isAltered = isAltered;
        this.isFirstEd = isFirstEd;
    }

    public int getIdWant() {
        return idWant;
    }

    public void setIdWant(int idWant) {
        this.idWant = idWant;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(long wishPrice) {
        this.wishPrice = wishPrice;
    }

    public long getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(long fromPrice) {
        this.fromPrice = fromPrice;
    }

    public boolean isMailAlert() {
        return mailAlert;
    }

    public void setMailAlert(boolean mailAlert) {
        this.mailAlert = mailAlert;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Language> getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(List<Language> idLanguage) {
        this.idLanguage = idLanguage;
    }

    public Condition getMinCondition() {
        return minCondition;
    }

    public void setMinCondition(Condition minCondition) {
        this.minCondition = minCondition;
    }

    public boolean isFoil() {
        return isFoil;
    }

    public void setFoil(boolean foil) {
        isFoil = foil;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public boolean isAltered() {
        return isAltered;
    }

    public void setAltered(boolean altered) {
        isAltered = altered;
    }

    public boolean isFirstEd() {
        return isFirstEd;
    }

    public void setFirstEd(boolean firstEd) {
        isFirstEd = firstEd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WantslistItem that = (WantslistItem) o;
        return idWant == that.idWant && count == that.count && wishPrice == that.wishPrice && fromPrice == that.fromPrice && mailAlert == that.mailAlert && idProduct == that.idProduct && isFoil == that.isFoil && isSigned == that.isSigned && isAltered == that.isAltered && isFirstEd == that.isFirstEd && type == that.type && product.equals(that.product) && Objects.equals(idLanguage, that.idLanguage) && minCondition == that.minCondition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWant, count, wishPrice, fromPrice, mailAlert, type, idProduct, product, idLanguage, minCondition, isFoil, isSigned, isAltered, isFirstEd);
    }

    @Override
    public String toString() {
        return "WantslistItem{" +
                "idWant=" + idWant +
                ", count=" + count +
                ", wishPrice=" + wishPrice +
                ", fromPrice=" + fromPrice +
                ", mailAlert=" + mailAlert +
                ", type=" + type +
                ", idProduct=" + idProduct +
                ", product=" + product +
                ", idLanguage=" + idLanguage +
                ", minCondition=" + minCondition +
                ", isFoil=" + isFoil +
                ", isSigned=" + isSigned +
                ", isAltered=" + isAltered +
                ", isFirstEd=" + isFirstEd +
                '}';
    }
}
