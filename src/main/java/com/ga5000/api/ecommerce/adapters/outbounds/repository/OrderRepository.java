package com.ga5000.api.ecommerce.adapters.outbounds.repository;

import com.ga5000.api.ecommerce.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
