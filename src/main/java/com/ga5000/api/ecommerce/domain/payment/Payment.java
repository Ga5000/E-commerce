package com.ga5000.api.ecommerce.domain.payment;

import com.ga5000.api.ecommerce.domain.order.Order;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order;



    public Payment() {
    }


}