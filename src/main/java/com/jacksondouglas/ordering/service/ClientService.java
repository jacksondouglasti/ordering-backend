package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Address;
import com.jacksondouglas.ordering.domain.City;
import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.enums.ClientType;
import com.jacksondouglas.ordering.dto.ClientDTO;
import com.jacksondouglas.ordering.dto.ClientNewDTO;
import com.jacksondouglas.ordering.exception.DataIntegrityException;
import com.jacksondouglas.ordering.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.AddressRepository;
import com.jacksondouglas.ordering.repository.CityRepository;
import com.jacksondouglas.ordering.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Client findById(Integer id) {
        Client client = clientRepository.findOne(id);

        if (client == null) {
            throw new ObjectNotFoundException("Client not found. Id" + id);
        }
        return client;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    public Client save(Client client) {
        client.setId(null);
        client = clientRepository.save(client);
        addressRepository.save(client.getAddresses());
        return client;
    }

    public Client update(Client client) {
        Client curr = findById(client.getId());
        curr.setName(client.getName());
        curr.setEmail(client.getEmail());

        return clientRepository.save(curr);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            clientRepository.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("It's not possible to delete a Client that has Purchases.");
        }
    }

    public Client fromDTO(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null);
    }

    public Client fromDTO(ClientNewDTO clientNewDTO) {
        Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), clientNewDTO.getCpfCnpj(), ClientType.toEnum(clientNewDTO.getType()));
        City city = cityRepository.findOne(clientNewDTO.getCityId());
        Address address = new Address(null, clientNewDTO.getStreet(), clientNewDTO.getNumber(), clientNewDTO.getNeighborhood(), clientNewDTO.getZipcode(), client, city);

        client.addAddress(address);
        client.addPhonenumber(clientNewDTO.getPhonenumber1());

        if (clientNewDTO.getPhonenumber2() != null) {
            client.addPhonenumber(clientNewDTO.getPhonenumber2());
        }
        if (clientNewDTO.getPhonenumber3() != null) {
            client.addPhonenumber(clientNewDTO.getPhonenumber3());
        }
        return client;
    }
}
