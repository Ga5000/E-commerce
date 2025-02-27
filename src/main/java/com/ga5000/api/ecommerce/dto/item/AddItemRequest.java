package com.ga5000.api.ecommerce.dto.item;

import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record AddItemRequest(
        UUID productId,
        @Positive(message = "Quantity must be a positive number") int quantity
) {
}
