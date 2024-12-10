package com.ga5000.api.ecommerce.application.usecases.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.category.Category}
 */
public record CategoryRequestDto(@NotNull @NotBlank String categoryName) implements Serializable {
}