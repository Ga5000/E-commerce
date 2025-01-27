package com.ga5000.api.ecommerce.dto.product;

import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.dto.image.ImageResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.Product}
 */
public record ProductResponseDto(UUID productId, @NotBlank(message = "Name is mandatory") String name,
                                 String description, @Positive(message = "Price must be greater than 0") double price,
                                 @PositiveOrZero(message = "Stock must be greater than or equal to 0") int stock,
                                 @PositiveOrZero(message = "Discount must be greater or equal to 0") int discount,
                                 List<ImageResponseDto> images,
                                 Set<CategoryResponseDto> categories) implements Serializable {
}