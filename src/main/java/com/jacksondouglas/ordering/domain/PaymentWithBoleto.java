package com.jacksondouglas.ordering.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.jacksondouglas.ordering.domain.enums.PaymentState;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("paymentWithBoleto")
public class PaymentWithBoleto extends Payment {
    private static final long serialVersionUID = 1L;

    private Date dueDate;
    private Date paymentDate;

    public PaymentWithBoleto() {
    }

    public PaymentWithBoleto(Integer id, PaymentState state, Purchase purchase, Date dueDate, Date paymentDate) {
        super(id, state, purchase);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
