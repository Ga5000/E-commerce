package com.ga5000.api.ecommerce.application.usecases.product.dto;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.image.Image;
import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Product}
 */
public record ProductRequestDTO(@NotBlank String productName, @NotBlank String productDescription,
                                @Min(value = 1) @Positive double productPrice, @Min(value = 1) @Positive int remainingProductQuantity,
                                @NotNull Image productImage,
                                @NotNull @Size List<Category> categories) implements Serializable {
}