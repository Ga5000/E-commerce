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
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    @ElementCollection
    @CollectionTable(name = "order_details", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyColumn(name = "product_name")
    @Column(name = "quantity", nullable = false)
    private Map<String, Integer> details = new HashMap<>(); // (product name, quantity)

    @Column(nullable = false)
    private double total; // Auto-calculated based on cart total price.

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(nullable = false)
    private LocalDateTime orderDate; // Auto-generated

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "paymentId", nullable = false)
    private Payment payment;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "addressId", nullable = false)
    private Address shippingAddress;

    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(User user, Cart cart, OrderStatus orderStatus, Payment payment, Address shippingAddress) {
        this();
        this.user = user;
        this.orderStatus = orderStatus;
        this.payment = payment;
        this.shippingAddress = shippingAddress;
        setDetails(cart);
        setTotal(cart);

    }

    private void setTotal(Cart cart) {
        if (cart != null) {
            this.total = cart.getTotalPrice();
        }
    }

    private void setDetails(Cart cart) {
        if (cart != null) {
            for (ProductItem productItem : cart.getItems()) {
                this.details.put(productItem.getName(), productItem.getQuantity());
            }
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
        return Collections.unmodifiableMap(details);
    }

    public double getTotal() {
        return total;
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

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
}
