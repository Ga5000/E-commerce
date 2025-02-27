package com.ga5000.api.ecommerce.dto.cart;

import com.ga5000.api.ecommerce.dto.item.cart.CartItemResponse;

import java.util.List;
import java.util.UUID;

public record CartResponse(UUID cartId, List<CartItemResponse> cartItems, double totalAmount) {
}
