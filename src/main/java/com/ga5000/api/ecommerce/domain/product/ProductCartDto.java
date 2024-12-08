package com.ga5000.api.ecommerce.domain.product;

import com.ga5000.api.ecommerce.domain.image.ImageDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link Product}
 */
public record ProductCartDto(@NotNull UUID productId, @NotNull @NotEmpty @NotBlank String productName,
                             @Positive double productPrice, @NotNull ImageDTO productImage) implements Serializable {
}