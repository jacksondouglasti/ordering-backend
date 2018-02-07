package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Purchase;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendPurchaseConfirmationEmail(Purchase purchase);

    void sendEmail(SimpleMailMessage mail);
}
