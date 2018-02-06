package com.jacksondouglas.ordering.dto;

import com.jacksondouglas.ordering.domain.Client;
import com.jacksondouglas.ordering.service.validation.ClientUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClientUpdate
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Required")
    @Length(min = 5, max = 120, message = "The length must be between 5 and 120")
    private String name;

    @NotEmpty(message = "Required")
    @Email(message = "Its not a valid email")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.email = client.getEmail();
        this.name = client.getName();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
