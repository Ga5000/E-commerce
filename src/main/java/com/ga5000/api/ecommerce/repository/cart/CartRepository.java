package com.ga5000.api.ecommerce.repository.cart;

import com.ga5000.api.ecommerce.domain.transaction.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}
