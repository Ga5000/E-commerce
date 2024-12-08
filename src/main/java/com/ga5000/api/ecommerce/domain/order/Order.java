package com.ga5000.api.ecommerce.domain.order;

import com.ga5000.api.ecommerce.domain.baseEntity.purchase.AbstractPurchase;
import com.ga5000.api.ecommerce.domain.baseEntity.payment.Payment;
import com.ga5000.api.ecommerce.domain.baseEntity.purchase.PurchaseItem;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order extends AbstractPurchase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private Payment payment;

    public Order() {}

    public Order(Set<PurchaseItem> items, User user, double totalPrice, int totalItems, UUID orderId, Payment payment) {
        super(items, user, totalPrice, totalItems);
        this.orderId = orderId;
        this.payment = payment;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    @Override
    public void addItem(Product product, int quantity) {
        Set<PurchaseItem> cartItems = getUser().getCart().getItems();
        for (PurchaseItem item : cartItems) {
            getItems().add(item);
        }

        updateTotalPrice();
        updateTotalItems();
    }

}
