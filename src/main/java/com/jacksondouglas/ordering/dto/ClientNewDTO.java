package com.jacksondouglas.ordering.dto;

import com.jacksondouglas.ordering.service.validation.ClientInsert;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ClientInsert
public class ClientNewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Required")
    @Length(min = 5, max = 120, message = "The length must be between 5 and 120")
    private String name;

    @NotEmpty(message = "Required")
    @Email(message = "Its not a valid email")
    private String email;

    @NotEmpty(message = "Required")
    private String cpfCnpj;

    private Integer type;

    @NotEmpty(message = "Required")
    private String street;

    @NotEmpty(message = "Required")
    private String number;

    @NotEmpty(message = "Required")
    private String neighborhood;

    @NotEmpty(message = "Required")
    private String zipcode;

    @NotEmpty(message = "Required")
    private String phonenumber1;

    private String phonenumber2;

    private String phonenumber3;

    private Integer cityId;

    public ClientNewDTO() {
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getPhonenumber1() {
        return phonenumber1;
    }

    public void setPhonenumber1(String phonenumber1) {
        this.phonenumber1 = phonenumber1;
    }

    public String getPhonenumber2() {
        return phonenumber2;
    }

    public void setPhonenumber2(String phonenumber2) {
        this.phonenumber2 = phonenumber2;
    }

    public String getPhonenumber3() {
        return phonenumber3;
    }

    public void setPhonenumber3(String phonenumber3) {
        this.phonenumber3 = phonenumber3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
