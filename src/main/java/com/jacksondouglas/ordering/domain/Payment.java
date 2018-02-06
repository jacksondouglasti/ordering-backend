package com.jacksondouglas.ordering.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jacksondouglas.ordering.domain.enums.PaymentState;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer state;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "purchase_id")
    @MapsId
    private Purchase purchase;

    public Payment() {
    }

    public Payment(Integer id, PaymentState state, Purchase purchase) {
        this.id = id;
        this.state = (state == null) ? null : state.getId();
        this.purchase = purchase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentState getState() {
        return PaymentState.toEnum(state);
    }

    public void setState(PaymentState state) {
        this.state = state.getId();
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
