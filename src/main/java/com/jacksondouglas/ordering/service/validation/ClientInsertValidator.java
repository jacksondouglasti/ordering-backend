package com.jacksondouglas.ordering.service.validation;

import com.jacksondouglas.ordering.controllers.exception.FieldMessage;
import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.domain.enums.ClientType;
import com.jacksondouglas.ordering.dto.ClientNewDTO;
import com.jacksondouglas.ordering.repository.ClientRepository;
import com.jacksondouglas.ordering.service.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public void initialize(ClientInsert ann) {
    }

    @Override
    public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(ClientType.PHYSICALPERSON.getId()) && !BR.isValidCPF(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "Invalid CPF"));
        }
        if (objDto.getType().equals(ClientType.LEGALPERSON.getId()) && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "Invalid CNPJ"));
        }

        Client clientAux = clientRepository.findByEmail(objDto.getEmail());

        if (clientAux != null) {
            list.add(new FieldMessage("email", "Email already exists"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }

}