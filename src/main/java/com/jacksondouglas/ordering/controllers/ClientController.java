package com.jacksondouglas.ordering.controllers;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }
}
