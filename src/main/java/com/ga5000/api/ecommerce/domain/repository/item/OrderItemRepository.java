package com.ga5000.api.ecommerce.domain.repository.item;

import com.ga5000.api.ecommerce.domain.model.item.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
}
