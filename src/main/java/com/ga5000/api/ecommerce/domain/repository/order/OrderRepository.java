package com.ga5000.api.ecommerce.domain.repository.order;

import com.ga5000.api.ecommerce.domain.model.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
}
