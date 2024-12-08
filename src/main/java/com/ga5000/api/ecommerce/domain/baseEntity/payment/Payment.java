package com.ga5000.api.ecommerce.domain.baseEntity.payment;

import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "payment_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Payment {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @OneToOne(mappedBy = "payment")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Payment(){}

    public Payment(UUID paymentId, Order order, User user, PaymentType paymentType, double totalAmount, Status status) {
        this.paymentId = paymentId;
        this.order = order;
        this.user = user;
        this.paymentType = paymentType;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Payment(PaymentType paymentType, double amount, Status status) {
        this.paymentType = paymentType;
        this.totalAmount = amount;
        this.status = status;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
