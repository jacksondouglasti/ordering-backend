package com.jacksondouglas.ordering.service;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.dto.ClientDTO;
import com.jacksondouglas.ordering.dto.ClientNewDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

public interface IClientService {
    Client findById(Integer id);

    List<Client> findAll();

    Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

    Client save(Client client);

    Client update(Client client);

    void delete(Integer id);

    Client fromDTO(ClientDTO clientDTO);

    Client fromDTO(ClientNewDTO clientNewDTO);

    URI uploadProfilePicture(MultipartFile multipartFile);

    Client findByEmail(String email);
}
