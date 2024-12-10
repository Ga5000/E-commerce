package com.ga5000.api.ecommerce.application.usecases.image;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.image.Image}
 */
public record ImageResponseDto(@NotNull UUID imageId, @NotNull String imageUrl) implements Serializable {
}