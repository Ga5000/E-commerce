package com.ga5000.api.ecommerce.application.usecases.category.dto;

import java.util.UUID;
/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.category.Category}
 */
public record CategoryResponseDTO(UUID categoryId, String name) {
}
