package com.ga5000.api.ecommerce.service.cart;

import com.ga5000.api.ecommerce.dto.cart.CartResponse;
import com.ga5000.api.ecommerce.dto.item.AddItemRequest;
import com.ga5000.api.ecommerce.exception.item.ItemAlreadyOnCartException;

import java.util.UUID;

public interface ICartService {
    void addItemToCart(AddItemRequest addItemRequest) throws ItemAlreadyOnCartException;
    void removeItemFromCart(UUID itemId);
    void updateQuantity(UUID itemId);
    void checkout();
    CartResponse getCart();

}
