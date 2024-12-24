package com.ga5000.api.ecommerce.domain.payment;

import com.ga5000.api.ecommerce.domain.order.Order;
import com.ga5000.api.ecommerce.domain.payment.utils.PaymentStatus;
import com.ga5000.api.ecommerce.domain.payment.utils.PaymentType;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Order order;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private User user;

    public Payment() {
        this.paymentDate = LocalDateTime.now();
    }

    public Payment(PaymentType paymentType, double amount, PaymentStatus status, User user) {
        this();
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public Payment(UUID paymentId, PaymentType paymentType, double amount, PaymentStatus status, LocalDateTime paymentDate, Order order, User user) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate != null ? paymentDate : LocalDateTime.now();
        this.order = order;
        this.user = user;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
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
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        this.user = user;
    }


    public boolean isSuccessful() {
        return PaymentStatus.APPROVED.equals(this.status);
    }

    public boolean isPending() {
        return PaymentStatus.PENDING.equals(this.status);
    }

    public boolean isFailed() {
        return PaymentStatus.REJECTED.equals(this.status);
    }

}
