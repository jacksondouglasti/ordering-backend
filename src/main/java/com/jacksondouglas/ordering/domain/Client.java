package com.jacksondouglas.ordering.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jacksondouglas.ordering.domain.enums.ClientType;
import com.jacksondouglas.ordering.domain.enums.Profile;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String cpfCnpj;
    private Integer type;

    @JsonIgnore

    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONENUMBER")
    private Set<String> phonenumbers = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PROFILES")
    private Set<Integer> profiles = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Purchase> purchases = new ArrayList<>();

    public Client() {
        addProfile(Profile.CLIENT);
    }

    public Client(Integer id, String name, String email, String cpfCnpj, ClientType type, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.type = (type == null) ? null : type.getId();
        this.password = password;
        addProfile(Profile.CLIENT);
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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public ClientType getType() {
        return ClientType.toEnum(type);
    }

    public void setType(ClientType type) {
        this.type = type.getId();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public Set<String> getPhonenumbers() {
        return phonenumbers;
    }

    public void addPhonenumber(String phonenumber) {
        this.phonenumbers.add(phonenumber);
    }

    public List<Purchase> getPurchases() {
        return Collections.unmodifiableList(purchases);
    }

    public void setPurchases(Purchase purchase) {
        this.purchases.add(purchase);
    }

    public Set<Profile> getProfiles() {
        return profiles.stream().map(p -> Profile.toEnum(p)).collect(Collectors.toSet());
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
