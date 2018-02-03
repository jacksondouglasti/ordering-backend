package com.jacksondouglas.ordering.domain;

import com.jacksondouglas.ordering.domain.enums.PaymentState;

import javax.persistence.Entity;

@Entity
public class PaymentWithCard extends Payment{
    private static final long serialVersionUID = 1L;

    private Integer installments;

    public PaymentWithCard() {
    }

    public PaymentWithCard(Integer id, PaymentState state, Purchase purchase, Integer installments) {
        super(id, state, purchase);
        this.installments = installments;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }
}
