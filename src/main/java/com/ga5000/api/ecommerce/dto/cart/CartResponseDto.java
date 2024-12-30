package com.ga5000.api.ecommerce.dto.cart;

import com.ga5000.api.ecommerce.domain.product.productItem.ProductItem;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.cart.Cart}
 */
public record CartResponseDto(UUID cartId, List<ProductItem> items, double totalPrice) implements Serializable {
}