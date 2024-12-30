package com.ga5000.api.ecommerce.service.cart;

import com.ga5000.api.ecommerce.dto.cart.CartResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public interface ICartService {
    void addProductToCart(UUID cartId, UUID productId) throws EntityNotFoundException;
    void removeProductFromCart(UUID cartId, int index) throws EntityNotFoundException;
    void updateQuantity(UUID cartId, int index, int newQuantity);
    void clearCart(UUID cartId) throws EntityNotFoundException;

    CartResponseDto getCart();
}
