package com.ga5000.api.ecommerce.domain.address;

import com.ga5000.api.ecommerce.domain.transaction.order.Order;
import com.ga5000.api.ecommerce.domain.user.client.Client;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "address_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID addressId;

    @NotBlank(message = "Postal code must not be blank")
    @Column(name = "postal_code", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String postalCode;

    @NotBlank(message = "Street must not be blank")
    @Column(name = "street", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String street;

    @NotBlank(message = "Number must not be blank")
    @Column(name = "number", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String number;

    @Column(name = "complement")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String complement;

    @NotBlank(message = "Neighborhood must not be blank")
    @Column(name = "neighborhood", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String neighborhood;

    @NotBlank(message = "City must not be blank")
    @Column(name = "city", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String city;

    @NotBlank(message = "State must not be blank")
    @Column(name = "state", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String state;

    @NotBlank(message = "Region must not be blank")
    @Column(name = "region", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String region;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Address(UUID addressId, String postalCode, String street, String number, String complement,
                   String neighborhood, String city, String state, String region, Client client, Order order) {
        this.addressId = addressId;
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.region = region;
        this.client = client;
        this.order = order;
    }

    public Address(String postalCode, String street, String number, String complement, String neighborhood,
                   String city, String state, String region, Client client, Order order) {
        this.postalCode = postalCode;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.region = region;
        this.client = client;
        this.order = order;
    }

    public Address() {
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }
}
