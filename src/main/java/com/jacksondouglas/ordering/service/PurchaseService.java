package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.PaymentWithBoleto;
import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.domain.PurchaseItem;
import com.jacksondouglas.ordering.domain.enums.PaymentState;
import com.jacksondouglas.ordering.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.PaymentRepository;
import com.jacksondouglas.ordering.repository.ProductRepository;
import com.jacksondouglas.ordering.repository.PurchaseItemRepository;
import com.jacksondouglas.ordering.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    public Purchase findById(Integer id) {
        Purchase purchase = purchaseRepository.findOne(id);

        if (purchase == null) {
            throw new ObjectNotFoundException("Purchase not found. Id: " + id);
        }
        return purchase;
    }

    public Purchase save(Purchase purchase) {
        purchase.setId(null);
        purchase.setInstant(Calendar.getInstance().getTime());
        purchase.getPayment().setState(PaymentState.PENDING);
        purchase.getPayment().setPurchase(purchase);

        if (purchase.getPayment() instanceof PaymentWithBoleto) {
            PaymentWithBoleto payment = (PaymentWithBoleto) purchase.getPayment();
            boletoService.fillPaymentWithBoleto(payment, purchase.getInstant());
        }

        purchase = purchaseRepository.save(purchase);
        paymentRepository.save(purchase.getPayment());

        for (PurchaseItem item : purchase.getItems()) {
            item.setDiscount(0.0);
            item.setPrice(productRepository.findOne(item.getProduct().getId()).getPrice());
            item.setPurchase(purchase);
        }

        purchaseItemRepository.save(purchase.getItems());
        return purchase;
    }
}
