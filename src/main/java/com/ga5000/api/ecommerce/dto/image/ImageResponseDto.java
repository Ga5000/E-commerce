package com.ga5000.api.ecommerce.dto.image;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.image.Image}
 */
public record ImageResponseDto(UUID imageId,
                               @NotNull(message = "URL is mandatory") @NotBlank(message = "URL is mandatory") String url) implements Serializable {
}