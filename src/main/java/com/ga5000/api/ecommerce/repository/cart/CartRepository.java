package com.ga5000.api.ecommerce.repository.cart;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
}
