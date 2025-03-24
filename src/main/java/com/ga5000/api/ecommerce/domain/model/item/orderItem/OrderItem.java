package com.ga5000.api.ecommerce.domain.model.item.orderItem;

import com.ga5000.api.ecommerce.domain.model.item.Item;
import com.ga5000.api.ecommerce.domain.model.order.Order;
import com.ga5000.api.ecommerce.domain.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items_tb")
public class OrderItem extends Item {

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public OrderItem() {
        super();
    }

    public OrderItem(String name, Product product, int quantity, BigDecimal price, Order order) {
        super(name, product, quantity, price);
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
