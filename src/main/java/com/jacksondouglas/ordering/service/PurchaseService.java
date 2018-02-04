package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    public Purchase findById(Integer id) {
        Purchase purchase = purchaseRepository.findOne(id);

        if (purchase == null) {
            throw new ObjectNotFoundException("Purchase not found. Id: " + id);
        }
        return purchase;
    }
}
