package com.ga5000.api.ecommerce.domain.transaction.order;

import com.ga5000.api.ecommerce.domain.item.orderItem.OrderItem;
import com.ga5000.api.ecommerce.domain.payment.Payment;
import com.ga5000.api.ecommerce.domain.transaction.Transaction;
import com.ga5000.api.ecommerce.domain.user.client.Client;
import com.ga5000.api.ecommerce.domain.address.Address;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;
import java.util.UUID;

@Entity
public class Order extends Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID orderId;

    @Column(name = "order_status", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderItem> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Address deliveryAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;


    // Implement the abstract method from Transaction
    @Override
    protected Set<OrderItem> getItems() {
        return orderItems;
    }


}
