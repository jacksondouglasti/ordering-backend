package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.PaymentWithBoleto;

import java.util.Date;

public interface IBoletoService {
    void fillPaymentWithBoleto(PaymentWithBoleto payment, Date instant);
}
