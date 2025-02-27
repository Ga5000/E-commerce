package com.ga5000.api.ecommerce.repository.item;

import com.ga5000.api.ecommerce.domain.item.cartItem.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    @Query(value = "SELECT COUNT(*) > 0 FROM cart_item WHERE product_id = :productId AND cart_id = :cartId", nativeQuery = true)
    boolean isItemAlreadyOnCart(UUID productId, UUID cartId);

}
