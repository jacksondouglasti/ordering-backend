package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.service.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractIEmailService implements IEmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendPurchaseConfirmationEmail(Purchase purchase) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromPurchase(purchase);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPurchase(Purchase purchase) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(purchase.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Purchase confirmed! Id: " + purchase.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(purchase.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = prepareNewPassword(client, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPassword(Client client, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(client.getEmail());
        sm.setFrom(sender);
        sm.setSubject("New password");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("New password: " + newPass);
        return sm;
    }
}
