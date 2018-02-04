package com.jacksondouglas.ordering.controllers;

import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Purchase> findById(@PathVariable Integer id) {
        Purchase purchase = purchaseService.findById(id);
        return ResponseEntity.ok(purchase);
    }
}