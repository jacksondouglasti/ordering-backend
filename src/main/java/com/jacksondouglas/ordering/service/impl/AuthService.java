package com.jacksondouglas.ordering.service.impl;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.repository.ClientRepository;
import com.jacksondouglas.ordering.service.IAuthService;
import com.jacksondouglas.ordering.service.IEmailService;
import com.jacksondouglas.ordering.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IEmailService IEmailService;

    private Random rand = new Random();

    @Override
    public void sendNewPassword(String email) {
        Client client = clientRepository.findByEmail(email);

        if (client == null) {
            throw new ObjectNotFoundException("Email not found");
        }

        String newPass = newPassword();
        client.setPassword(bCryptPasswordEncoder.encode(newPass));

        clientRepository.save(client);
        IEmailService.sendNewPasswordEmail(client, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);

        switch (opt) {
            case 0: //Generates a number
                return (char) (rand.nextInt(10) + 48);
            case 1: //Generates a uppercase
                return (char) (rand.nextInt(26) + 65);
            default: //Generates a lowercase
                return (char) (rand.nextInt(26) + 97);
        }
    }
}
