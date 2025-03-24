package com.ga5000.api.ecommerce.domain.model.order;

import com.ga5000.api.ecommerce.domain.model.address.Address;
import com.ga5000.api.ecommerce.domain.model.item.orderItem.OrderItem;
import com.ga5000.api.ecommerce.domain.model.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders_tb")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    public Order() {}

    public Order(User client, Set<OrderItem> orderItems, Address address, BigDecimal totalPrice) {
        this.client = client;
        this.orderItems = orderItems;
        this.address = address;
        this.totalPrice = totalPrice;
    }

    public UUID getId() {
        return id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
