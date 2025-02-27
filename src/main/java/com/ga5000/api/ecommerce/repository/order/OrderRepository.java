package com.ga5000.api.ecommerce.repository.order;

import com.ga5000.api.ecommerce.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query(value = "SELECT o FROM orders o JOIN users u ON o.user_id = u.user_id WHERE u.email = :email", nativeQuery = true)
    List<Order> findOrdersByUserEmail(String email);

}
