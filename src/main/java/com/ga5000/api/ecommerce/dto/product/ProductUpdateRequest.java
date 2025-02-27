package com.ga5000.api.ecommerce.dto.product;

import jakarta.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public record ProductUpdateRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Description is required") String description,
        @Positive(message = "Price must be greater than zero") double price,
        @PositiveOrZero(message = "Stock cannot be negative") int stock,
        @NotEmpty(message = "At least one category is required") List<UUID> categoriesIds
) {
}
