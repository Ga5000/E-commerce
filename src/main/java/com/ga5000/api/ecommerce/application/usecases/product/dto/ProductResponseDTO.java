package com.ga5000.api.ecommerce.application.usecases.product.dto;

import com.ga5000.api.ecommerce.application.usecases.category.dto.CategoryResponseDTO;
import com.ga5000.api.ecommerce.domain.image.Image;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.Product}
 */
public record ProductResponseDTO(UUID productId, String productName, String productDescription, double productPrice,
                                 int remainingProductQuantity, Image productImage, List<CategoryResponseDTO> categories,
                                 boolean available) implements Serializable {
}