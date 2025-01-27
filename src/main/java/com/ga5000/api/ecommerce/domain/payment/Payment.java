package com.ga5000.api.ecommerce.domain.payment;

import com.ga5000.api.ecommerce.domain.transaction.order.Order;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID paymentId;

    @Column(name = "amount", nullable = false)
    @JdbcTypeCode(SqlTypes.DOUBLE)
    private double amount;

    @Column(name = "status", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String status;

    @Column(name = "payment_method", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String paymentMethod;

    @Column(name = "payment_date", nullable = false)
    @JdbcTypeCode(SqlTypes.TIMESTAMP)
    private LocalDateTime paymentDate;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public Payment(UUID paymentId, double amount, String status, String paymentMethod, Order order) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now();
        this.order = order;
    }

    public Payment() {
        this.paymentDate = LocalDateTime.now();
    }

    public Payment(double amount, String status, String paymentMethod, Order order) {
        this.amount = amount;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.paymentDate = LocalDateTime.now();
        this.order = order;
    }

    public UUID getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(UUID paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
