package com.ga5000.api.ecommerce.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.category.Category}
 */
public record CategoryResponseDto(UUID categoryId,
                                  @NotNull(message = "Name is mandatory") @NotBlank(message = "Name is mandatory") String name) implements Serializable {
}