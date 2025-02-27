package com.ga5000.api.ecommerce.domain.item.orderItem;

import com.ga5000.api.ecommerce.domain.item.Item;
import com.ga5000.api.ecommerce.domain.order.Order;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem extends Item {
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
}
