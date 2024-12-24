package com.ga5000.api.ecommerce.domain.address;

import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    @Column(nullable = false)
    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @Column(nullable = false)
    @NotBlank(message = "Rua é obrigatória")
    private String street;

    private String complement;

    @Column(nullable = false)
    @NotBlank(message = "Bairro é obrigatório")
    private String neighborhood;

    @Column(nullable = false)
    @NotBlank(message = "Cidade é obrigatória")
    private String city;

    @Column(nullable = false)
    @NotBlank(message = "Estado é obrigatório")
    private String state;

    @Column(nullable = false)
    @NotBlank(message = "País é obrigatório")
    private String country;

    @Transient
    private String formattedAddress;

    @ManyToMany(mappedBy = "addresses")
    private List<User> users;

    @OneToOne(mappedBy = "shippingAddress")
    private Order order;

    public Address() {}

    public Address(String cep, String street, String complement, String neighborhood,
                   String city, String state, String country) {
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.country = country;
        updateFormattedAddress();
    }

    @PostLoad
    private void updateFormattedAddress() {
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(street).append(", ")
                .append(neighborhood).append(", ")
                .append(city).append(", ")
                .append(state).append(", ")
                .append(country);

        if (complement != null && !complement.isBlank()) {
            addressBuilder.append(" - ").append(complement);
        }

        this.formattedAddress = addressBuilder.toString();
    }

    public UUID getAddressId() {
        return addressId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
        updateFormattedAddress();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        updateFormattedAddress();
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
        updateFormattedAddress();
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
        updateFormattedAddress();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        updateFormattedAddress();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        updateFormattedAddress();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        updateFormattedAddress();
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
