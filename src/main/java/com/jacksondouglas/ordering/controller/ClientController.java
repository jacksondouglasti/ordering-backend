package com.jacksondouglas.ordering.controller;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.dto.ClientDTO;
import com.jacksondouglas.ordering.dto.ClientNewDTO;
import com.jacksondouglas.ordering.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Client> findById(@PathVariable Integer id) {
        Client client = clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClientDTO>> findAll() {
        List<ClientDTO> clientsDTO = clientService.findAll().stream().map(c -> new ClientDTO(c)).collect(Collectors.toList());
        return ResponseEntity.ok(clientsDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Client> clients = clientService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClientDTO> clientsDTO = clients.map(c -> new ClientDTO(c));
        return ResponseEntity.ok(clientsDTO);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody ClientNewDTO clientNewDTO) {
        Client client = clientService.save(clientService.fromDTO(clientNewDTO));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Integer id) {
        clientDTO.setId(id);
        clientService.update(clientService.fromDTO(clientDTO));
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
