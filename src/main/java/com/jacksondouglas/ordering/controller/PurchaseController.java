package com.jacksondouglas.ordering.controller;

import com.jacksondouglas.ordering.domain.Purchase;
import com.jacksondouglas.ordering.service.IPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/purchases")
public class PurchaseController {

    @Autowired
    private IPurchaseService purchaseService;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<Purchase>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "instant") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction) {
        Page<Purchase> purchases = purchaseService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok(purchases);
    }
}
