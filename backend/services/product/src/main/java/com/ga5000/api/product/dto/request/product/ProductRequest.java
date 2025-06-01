package com.ga5000.api.product.dto.request.product;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank(message = "Product name is required")
        String name
) {
}
