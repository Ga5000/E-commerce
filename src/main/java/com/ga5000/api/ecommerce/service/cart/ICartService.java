package com.ga5000.api.ecommerce.service.cart;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.dto.cart.AddItemDto;
import com.ga5000.api.ecommerce.dto.cart.CartResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public interface ICartService {
    void addItemToCart(AddItemDto addItemDto) throws EntityNotFoundException;
    void removeItemFromCart(UUID cartId, int index) throws EntityNotFoundException;
    void clearCart(UUID cartId) throws EntityNotFoundException;

    void updateItemQuantity(UUID cartId, int index) throws EntityNotFoundException;
    CartResponseDto displayCart(UUID userId) throws EntityNotFoundException;

}
