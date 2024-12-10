package com.ga5000.api.ecommerce.application.usecases.product.dto;

import com.ga5000.api.ecommerce.application.usecases.image.dto.ImageDTO;
import com.ga5000.api.ecommerce.domain.product.Product;
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
                             @Positive double productPrice, @NotNull ImageDTO productImage, int quantity) implements Serializable {
}