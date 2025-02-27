package com.ga5000.api.ecommerce.dto.product;

import jakarta.validation.constraints.*;
import java.util.List;
import java.util.UUID;

public record ProductRequest(
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Description is required") String description,
        @Positive(message = "Price must be greater than zero") double price,
        @PositiveOrZero(message = "Stock cannot be negative") int stock,
        @Min(value = 0, message = "Discount cannot be negative")
        @Max(value = 100, message = "Discount cannot be greater than 100") int discount,
        @NotEmpty(message = "At least one category is required") List<UUID> categoriesIds
) {
}
