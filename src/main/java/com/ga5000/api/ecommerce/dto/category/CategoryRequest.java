package com.ga5000.api.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank(message = "Name is required") String name) {
}
