package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findById(Integer id) {
        Client client = clientRepository.findOne(id);

        if (client == null) {
            throw new ObjectNotFoundException("Client not found. Id" + id);
        }
        return client;
    }
}
