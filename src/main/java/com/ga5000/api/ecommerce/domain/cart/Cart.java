package com.ga5000.api.ecommerce.domain.cart;

import com.ga5000.api.ecommerce.domain.item.Item;
import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
public class Cart{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Cart(UUID cartId, List<CartItem> cartItems, User user) {
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.user = user;
    }

    public Cart(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Cart(User user){
        this.user = user;
    }

    public Cart() {
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalAmount(){
        return cartItems.stream().mapToDouble(Item::getTotalItemPrice).sum();
    }

    public void addItem(CartItem item) {
        cartItems.add(item);
    }

    public void removeItem(CartItem item) {
        cartItems.remove(item);
    }



}

