package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.Address;
import com.jacksondouglas.ordering.domain.City;
import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.enums.ClientType;
import com.jacksondouglas.ordering.domain.enums.Profile;
import com.jacksondouglas.ordering.dto.ClientDTO;
import com.jacksondouglas.ordering.dto.ClientNewDTO;
import com.jacksondouglas.ordering.security.UserSS;
import com.jacksondouglas.ordering.service.IClientService;
import com.jacksondouglas.ordering.service.exception.AuthorizationException;
import com.jacksondouglas.ordering.service.exception.DataIntegrityException;
import com.jacksondouglas.ordering.service.exception.ObjectNotFoundException;
import com.jacksondouglas.ordering.repository.AddressRepository;
import com.jacksondouglas.ordering.repository.CityRepository;
import com.jacksondouglas.ordering.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;

@Service
public class ClientService implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private S3Service s3Service;

    @Autowired
    private ImageService imageService;

    @Value("${img.prefix.client.profile}")
    private String prefix;

    @Value("${img.profile.size}")
    private Integer size;

    @Override
    public Client findById(Integer id) {
        UserSS user = UserService.authenticated();

        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Access denied");
        }

        Client client = clientRepository.findOne(id);

        if (client == null) {
            throw new ObjectNotFoundException("Client not found. Id" + id);
        }
        return client;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    @Override
    public Client save(Client client) {
        client.setId(null);
        client = clientRepository.save(client);
        addressRepository.save(client.getAddresses());
        return client;
    }

    @Override
    public Client update(Client client) {
        Client curr = findById(client.getId());
        curr.setName(client.getName());
        curr.setEmail(client.getEmail());

        return clientRepository.save(curr);
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        try {
            clientRepository.delete(id);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityException("It's not possible to delete a Client that has Purchases.");
        }
    }

    @Override
    public Client fromDTO(ClientDTO clientDTO) {
        return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null, null);
    }

    @Override
    public Client fromDTO(ClientNewDTO clientNewDTO) {
        Client client = new Client(null, clientNewDTO.getName(), clientNewDTO.getEmail(), clientNewDTO.getCpfCnpj(), ClientType.toEnum(clientNewDTO.getType()), bCryptPasswordEncoder.encode(clientNewDTO.getPassword()));
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

    @Override
    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();

        if (user == null) {
            throw new AuthorizationException("Access denied");
        }

        BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
        jpgImage = imageService.cropSquare(jpgImage);
        jpgImage = imageService.resize(jpgImage, size);

        String fileName = prefix + user.getId() + ".jpg";

        return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");
    }

    @Override
    public Client findByEmail(String email) {
        UserSS user = UserService.authenticated();

        if (user == null ||!user.hasRole(Profile.ADMIN) && !email.equals(user.getUsername())) {
            throw new AuthorizationException("Access denied");
        }

        Client client = clientRepository.findOne(user.getId());

        if (client == null) {
            throw new ObjectNotFoundException("Object not found! Id: " + user.getId() + ", Type: " + Client.class.getName());
        }
        return client;
    }
}
