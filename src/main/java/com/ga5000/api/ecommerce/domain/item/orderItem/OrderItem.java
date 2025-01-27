package com.ga5000.api.ecommerce.domain.item.orderItem;

import com.ga5000.api.ecommerce.domain.item.Item;
import com.ga5000.api.ecommerce.domain.transaction.order.Order;
import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderItem extends Item {

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;  // The order that the item is in

    public OrderItem(Product product, int quantity, double unitPrice, Order order) {
        super(product, quantity, unitPrice);
        this.order = order;
    }

    public OrderItem(Order order) {
        this.order = order;
    }

    public OrderItem() {
        super();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
