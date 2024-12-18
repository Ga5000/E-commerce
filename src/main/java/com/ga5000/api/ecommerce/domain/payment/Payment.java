package com.ga5000.api.ecommerce.domain.payment;

import ch.qos.logback.core.status.Status;
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

    @Column(nullable = false)
    private LocalDateTime paymentDate = LocalDateTime.now(); //auto-generated

    @OneToOne(mappedBy = "payment")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    public Payment(UUID paymentId, PaymentType paymentType, double amount, PaymentStatus status,
                   LocalDateTime paymentDate, Order order, User user) {
        this.paymentId = paymentId;
        this.paymentType = paymentType;
        this.amount = amount;
        this.status = status;
        this.paymentDate = paymentDate;
        this.order = order;
        this.user = user;
    }

    public Payment() {}

    public Payment(PaymentType paymentType, double amount, Status status) {
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
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
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
}
