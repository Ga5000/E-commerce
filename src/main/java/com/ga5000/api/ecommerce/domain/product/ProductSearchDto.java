package com.ga5000.api.ecommerce.domain.product;

import com.ga5000.api.ecommerce.dto.image.ImageResponseDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link Product}
 */
public record ProductSearchDto(UUID productId, @NotBlank(message = "Name is mandatory") String name,
                               @Positive(message = "Price must be greater than 0") double price,
                               @PositiveOrZero(message = "Stock must be greater than or equal to 0") int stock,
                               @PositiveOrZero(message = "Discount must be greater or equal to 0") int discount,
                               List<ImageResponseDto> images) implements Serializable {
}