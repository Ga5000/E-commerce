package com.ga5000.api.ecommerce.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.user.customUserDetails.CustomUserDetails;
import com.ga5000.api.ecommerce.domain.user.role.Role;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(length = 120)
    private String firstName;

    @Column(length = 160)
    private String lastName;

    @Column(unique = true, length = 120)
    private String email;

    @JsonIgnore
    private String password;

    private LocalDate createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "userId")
    private Cart cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> deliveryAddresses = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String firstName, String lastName, String email, String password,
                List<Order> orders, Set<Address> deliveryAddresses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.cart = new Cart(this);
        this.orders = orders;
        this.deliveryAddresses = deliveryAddresses;
        this.role = Role.CUSTOMER;
    }

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDate.now();
        this.cart = new Cart(this);
        this.role = Role.ADMIN;
    }

    public User(){
        this.createdAt = LocalDate.now();
        this.cart = new Cart();
        this.role = Role.CUSTOMER;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Address> getDeliveryAddresses() {
        return deliveryAddresses;
    }

    public void setDeliveryAddresses(Set<Address> deliveryAddresses) {
        this.deliveryAddresses = deliveryAddresses;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
