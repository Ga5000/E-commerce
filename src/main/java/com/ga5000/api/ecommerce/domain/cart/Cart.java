package com.ga5000.api.ecommerce.domain.cart;

import com.ga5000.api.ecommerce.domain.product.productItem.ProductItem;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Cart {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID cartId;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private User user;

    @ElementCollection
    List<ProductItem> items = new ArrayList<>();

    @Column(nullable = false)
    private double totalPrice = calculateTotalPrice(items); //auto-updated

    @Column(nullable = false)
    private boolean isCheckedOut = false;

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
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    private double calculateTotalPrice(List<ProductItem> items){
        for (ProductItem item : items){
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public void addItem(ProductItem item){
        items.add(item);
    }

    public void removeItem(int index){
        items.remove(index);
    }

    public void clear(){
        items.clear();
    }

}
