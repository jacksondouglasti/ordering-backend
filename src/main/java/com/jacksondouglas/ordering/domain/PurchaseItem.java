package com.jacksondouglas.ordering.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class PurchaseItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private PurchaseItemPk id = new PurchaseItemPk();

    private Double discount;
    private Integer amount;
    private BigDecimal price;

    public PurchaseItem() {
    }

    public PurchaseItem(Purchase purchase, Product product, Double discount, Integer amount, BigDecimal price) {
        this.id.setPurchase(purchase);
        this.id.setProduct(product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    public BigDecimal getSubTotal() {
        return price.subtract(BigDecimal.valueOf(discount)).multiply(BigDecimal.valueOf(amount));
    }

    public PurchaseItemPk getId() {
        return id;
    }

    public void setId(PurchaseItemPk id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @JsonIgnore
    public Purchase getPurchase() {
        return id.getPurchase();
    }

    public void setPurchase(Purchase purchase) {
        id.setPurchase(purchase);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItem that = (PurchaseItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
