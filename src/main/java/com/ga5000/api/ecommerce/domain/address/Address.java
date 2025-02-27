package com.ga5000.api.ecommerce.domain.address;

import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID addressId;

    private String street;

    private String number;

    @Column(unique = true)
    private String zipCode;

    private String complement; // optional

    @ManyToMany(mappedBy = "deliveryAddresses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<User> users = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;

    public Address(UUID addressId, String street, String number, String zipCode, String complement, Set<User> users, Order order) {
        this.addressId = addressId;
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.complement = complement;
        this.users = users;
        this.order = order;
    }

    public Address(String street, String number, String zipCode, Order order) {
        this.street = street;
        this.number = number;
        this.zipCode = zipCode;
        this.order = order;
    }

    public Address() {
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getCompleteAddress(){
        return street + " " + number + " " + complement;
    }
}
