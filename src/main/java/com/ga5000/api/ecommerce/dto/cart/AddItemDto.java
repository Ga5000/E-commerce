package com.ga5000.api.ecommerce.dto.cart;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AddItemDto(@NotNull UUID cartId, @NotNull UUID productId) {
}
