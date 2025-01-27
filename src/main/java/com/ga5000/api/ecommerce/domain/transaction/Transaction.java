package com.ga5000.api.ecommerce.domain.transaction;

import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import com.ga5000.api.ecommerce.domain.item.orderItem.OrderItem;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@MappedSuperclass
public abstract class Transaction {

    @Column(name = "total_amount", nullable = false)
    @JdbcTypeCode(SqlTypes.DOUBLE)
    protected double totalAmount;

    // Abstract method for subclasses to provide their specific item set
    protected abstract Set<?> getItems();


    public Transaction(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Transaction() {
    }

    @PrePersist
    @PreUpdate
    private void calculateTotalAmount() {
        this.totalAmount = getItems().stream()
                .mapToDouble(item -> {
                    if (item instanceof CartItem) {
                        return ((CartItem) item).getUnitPrice();
                    } else if (item instanceof OrderItem) {
                        return ((OrderItem) item).getUnitPrice();
                    }
                    return 0.0;
                })
                .sum();
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    }

