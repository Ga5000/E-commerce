package com.ga5000.api.ecommerce.domain.order;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.order.utils.OrderStatus;
import com.ga5000.api.ecommerce.domain.payment.Payment;
import com.ga5000.api.ecommerce.domain.product.productItem.ProductItem;
import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.domain.address.Address;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_details", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_name")
    @Column(name = "quantity")
    private Map<String, Integer> details = new HashMap<>(); // (product name, quantity of said product)

    @Column(nullable = false)
    private double total; // Auto-generated (based on the total price of the cart)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private LocalDateTime orderDate = LocalDateTime.now(); //auto-generated

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address shippingAddress; // selected by the user

    public Order() {
    }

    public Order(UUID orderId, User user, Map<String, Integer> details, double total, OrderStatus orderStatus,
                 LocalDateTime orderDate, Payment payment, Address shippingAddress) {
        this.orderId = orderId;
        this.user = user;
        this.details = details;
        this.total = total;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.payment = payment;
        this.shippingAddress = shippingAddress;
    }

    private void setTotal(User user) {
        Cart cart = user.getCart();
        this.total = cart.getTotalPrice();
    }

    private void setDetails(User user) {
        Cart cart = user.getCart();
        for (ProductItem productItem : cart.getItems()) {
            this.details.put(productItem.getName(), productItem.getQuantity());
        }
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Map<String, Integer> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Integer> details) {
        this.details = details;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
