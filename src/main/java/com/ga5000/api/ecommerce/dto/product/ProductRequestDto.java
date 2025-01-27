package com.ga5000.api.ecommerce.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.Product}
 */
public record ProductRequestDto(@NotBlank(message = "Name is mandatory") String name, String description,
                                @Positive(message = "Price must be greater than 0") double price,
                                @PositiveOrZero(message = "Stock must be greater than or equal to 0") int stock,
                                @PositiveOrZero(message = "Discount must be greater or equal to 0") int discount,
                                @Size(min = 1, message = "A product must have at least one image")List<MultipartFile> files,
                                @Size(min = 1, message = "A product must have at least one category")List<UUID> categoriesIds) implements Serializable {
}