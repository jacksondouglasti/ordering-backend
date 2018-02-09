package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.Purchase;
import org.springframework.mail.SimpleMailMessage;

public interface IEmailService {

    void sendPurchaseConfirmationEmail(Purchase purchase);

    void sendEmail(SimpleMailMessage mail);

    void sendNewPasswordEmail(Client client, String newPass);
}
