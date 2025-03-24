package com.ga5000.api.ecommerce.domain.model.cart;

import com.ga5000.api.ecommerce.domain.model.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.model.user.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "carts_tb")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<CartItem> cartItems;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal totalPrice;

    public Cart() {}

    public Cart(User client, Set<CartItem> cartItems, BigDecimal totalPrice) {
        this.client = client;
        this.cartItems = cartItems;
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

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
