package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.PaymentWithBoleto;
import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.domain.PurchaseItem;
import com.jacksondouglas.ordering.domain.enums.PaymentState;
import com.jacksondouglas.ordering.security.UserSS;
import com.jacksondouglas.ordering.service.IPurchaseService;
import com.jacksondouglas.ordering.service.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.*;
import com.jacksondouglas.ordering.service.EmailService;
import com.jacksondouglas.ordering.service.IBoletoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PurchaseService implements IPurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private IBoletoService boletoService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public Purchase findById(Integer id) {
        Purchase purchase = purchaseRepository.findOne(id);

        if (purchase == null) {
            throw new ObjectNotFoundException("Purchase not found. Id: " + id);
        }
        return purchase;
    }

    @Override
    public Purchase save(Purchase purchase) {
        purchase.setId(null);
        purchase.setInstant(Calendar.getInstance().getTime());
        purchase.getPayment().setState(PaymentState.PENDING);
        purchase.getPayment().setPurchase(purchase);
        purchase.setClient(clientRepository.getOne(purchase.getClient().getId()));

        if (purchase.getPayment() instanceof PaymentWithBoleto) {
            PaymentWithBoleto payment = (PaymentWithBoleto) purchase.getPayment();
            boletoService.fillPaymentWithBoleto(payment, purchase.getInstant());
        }

        purchase = purchaseRepository.save(purchase);
        paymentRepository.save(purchase.getPayment());

        for (PurchaseItem item : purchase.getItems()) {
            item.setDiscount(0.0);
            item.setProduct(productRepository.findOne(item.getProduct().getId()));
            item.setPrice(item.getProduct().getPrice());
            item.setPurchase(purchase);
        }

        purchaseItemRepository.save(purchase.getItems());
        emailService.sendPurchaseConfirmationEmail(purchase);

        return purchase;
    }

    @Override
    public Page<Purchase> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();

        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Client client = clientRepository.findOne(user.getId());
        return purchaseRepository.findByClient(client, pageRequest);
    }
}
