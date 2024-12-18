package com.ga5000.api.ecommerce.domain.user;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.payment.Payment;
import com.ga5000.api.ecommerce.domain.address.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(nullable = false)
    @NotBlank(message = "O primeiro nome não pode estar em branco")
    private String firstName = ""; // default = blank

    @Column(nullable = false)
    @NotBlank(message = "O sobrenome não pode estar em branco")
    private String lastName = ""; // default = blank

    @Column(nullable = false, unique = true)
    @Email(message = "O e-mail deve ser válido")
    private String email = ""; // default = blank

    @Column(nullable = false)
    @NotBlank(message = "A senha não pode estar em branco")
    private String password = ""; // default = blank

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); //auto-generated

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(
            name = "user_addresses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart = new Cart(); // auto-generated

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments = new ArrayList<>(); // empty at first

    public User() {
    }

    public User(UUID userId, String firstName, String lastName, String email, String password, LocalDateTime createdAt,
                LocalDateTime updatedAt, List<Address> addresses, Cart cart, List<Order> orders, List<Payment> payments) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.addresses = addresses;
        this.cart = cart;
        this.orders = orders;
        this.payments = payments;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
