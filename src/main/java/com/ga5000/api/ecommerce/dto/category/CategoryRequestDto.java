package com.ga5000.api.ecommerce.dto.category;

import com.ga5000.api.ecommerce.domain.category.Category;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link Category}
 */
public record CategoryRequestDto(
        @NotBlank(message = "O nome da categoria não pode estar vazio") String categoryName) implements Serializable {
}