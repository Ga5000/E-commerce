package com.ga5000.api.ecommerce.dto.item.cart;

import com.ga5000.api.ecommerce.dto.image.ImageResponse;

import java.util.UUID;

public record CartItemResponse(UUID itemId, String productName, ImageResponse productImageResponse, double unitPrice, int quantity, double totalItemPrice) {
}
