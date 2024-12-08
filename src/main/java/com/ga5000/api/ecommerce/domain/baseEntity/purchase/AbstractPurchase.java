package com.ga5000.api.ecommerce.domain.baseEntity.purchase;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;


import java.util.Set;

@MappedSuperclass
public abstract class AbstractPurchase {

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PurchaseItem> items;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    private double totalPrice = 0;

    private int totalItems = 0;

    public AbstractPurchase() {
    }

    public AbstractPurchase(Set<PurchaseItem> items, User user, double totalPrice, int totalItems) {
        this.items = items;
        this.user = user;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }

    public Set<PurchaseItem> getItems() {
        return items;
    }

    public void setItems(Set<PurchaseItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public abstract void addItem(Product product, int quantity);

    protected void updateTotalPrice() {
        double price = 0;
        for (PurchaseItem item : getItems()) {
            price += item.getTotalItemPrice();
        }
        setTotalPrice(price);
    }
    protected void updateTotalItems() {
        int count = 0;
        for (PurchaseItem item : getItems()) {
            count += item.getQuantity();
        }
        setTotalItems(count);
    }

}
