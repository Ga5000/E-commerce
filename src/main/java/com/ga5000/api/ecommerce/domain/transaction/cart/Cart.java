package com.ga5000.api.ecommerce.domain.transaction.cart;

import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.transaction.Transaction;
import com.ga5000.api.ecommerce.domain.user.client.Client;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Entity
public class Cart extends Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cart_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID cartId;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItem> cartItems;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "userId")
    private Client client;

    public Cart(double totalAmount, UUID cartId, Set<CartItem> cartItems, Client client) {
        super(totalAmount);
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.client = client;
    }

    public Cart(double totalAmount, Set<CartItem> cartItems, Client client) {
        super(totalAmount);
        this.cartItems = cartItems;
        this.client = client;
    }

    public Cart() {
        super();
    }

    // Implement the abstract method from Transaction
    @Override
    protected Set<CartItem> getItems() {
        return cartItems;
    }


}
