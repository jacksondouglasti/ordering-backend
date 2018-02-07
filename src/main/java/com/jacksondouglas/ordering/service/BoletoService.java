package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.PaymentWithBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void fillPaymentWithBoleto(PaymentWithBoleto payment, Date instant) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setDueDate(calendar.getTime());
    }
}
