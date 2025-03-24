package com.ga5000.api.ecommerce.domain.model.address;

import com.ga5000.api.ecommerce.domain.model.order.Order;
import com.ga5000.api.ecommerce.domain.model.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "address_tb")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 8)
    private String cep; // Código de Endereçamento Postal (ZIP Code)

    @Column(nullable = false)
    private String street; // Logradouro (Rua, Avenida, etc.)

    @Column(nullable = false)
    private String number; // Número da residência

    private String complement; // Complemento (Apto, Bloco, etc.)

    @Column(nullable = false)
    private String neighborhood; // Bairro

    @Column(nullable = false)
    private String city; // Cidade

    @Column(nullable = false)
    private String state; // Estado (Sigla - SP, RJ, etc.)

    @Column(nullable = false)
    private String country; // País (Brasil)

    @ManyToMany(mappedBy = "addresses", fetch = FetchType.LAZY)
    private List<User> clients;

    @OneToOne(mappedBy = "address")
    private Order order;

    public Address() {}

    public Address(String cep, String street, String number, String complement, String neighborhood,
                   String city, String state, String country, List<User> clients, Order order) {
        this.cep = cep;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.clients = clients;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
