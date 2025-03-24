package com.ga5000.api.ecommerce.domain.repository.cart;

import com.ga5000.api.ecommerce.domain.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<Cart, UUID> {
}
