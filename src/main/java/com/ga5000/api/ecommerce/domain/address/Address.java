package com.ga5000.api.ecommerce.domain.address;

import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    private String street;
    private String city;
    private String state;
    private String neighborhood;
    private String zipCode; // CEP
    private String country;
    private String complement;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    public Address() {}

    public Address(UUID addressId, String street, String city, String state,
                   String neighborhood, String zipCode, String country, User user, String complement) {
        this.addressId = addressId;
        this.street = street;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.country = country;
        this.user = user;
        this.complement = complement;
    }
}
