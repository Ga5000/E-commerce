package com.ga5000.api.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.category.Category}
 */
public record CategoryResponseDto(UUID categoryId,
                                  @NotBlank(message = "O nome da categoria não pode estar vazio") String categoryName) implements Serializable {
}