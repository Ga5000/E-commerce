package com.ga5000.api.ecommerce.domain.order;

import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.domain.item.orderItem.OrderItem;
import com.ga5000.api.ecommerce.domain.order.status.OrderStatus;
import com.ga5000.api.ecommerce.domain.payment.Payment;
import com.ga5000.api.ecommerce.domain.user.User;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class Order{
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<Payment> payments;

    private double totalAmount;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
