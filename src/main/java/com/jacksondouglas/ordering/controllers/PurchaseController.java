package com.jacksondouglas.ordering.controllers;

import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody Purchase purchase) {
        purchase = purchaseService.save(purchase);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(purchase.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
