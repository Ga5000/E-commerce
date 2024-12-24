package com.ga5000.api.ecommerce.domain.cart;

import com.ga5000.api.ecommerce.domain.product.productItem.ProductItem;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private User user;

    @ElementCollection
    private List<ProductItem> items = new ArrayList<>();

    @Column(nullable = false)
    private double totalPrice;

    @Column(nullable = false)
    private boolean isCheckedOut = false;

    public Cart() {
        this.totalPrice = calculateTotalPrice();
    }

    public UUID getCartId() {
        return cartId;
    }

    public void setCartId(UUID cartId) {
        this.cartId = cartId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<ProductItem> getItems() {
        return items;
    }

    public void setItems(List<ProductItem> items) {
        this.items = items;
        this.totalPrice = calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public void addItem(ProductItem item) {
        items.add(item);
        this.totalPrice = calculateTotalPrice();
    }

    public void removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            this.totalPrice = calculateTotalPrice();
        }
    }

    public void clear() {
        items.clear();
        this.totalPrice = 0.0;
    }

    private double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
