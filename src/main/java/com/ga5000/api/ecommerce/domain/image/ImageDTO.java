package com.ga5000.api.ecommerce.domain.image;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Image}
 */
public record ImageDTO(@NotNull UUID imageId, @NotNull byte[] image) implements Serializable {
  }