package com.ga5000.api.ecommerce.utils.mapper;

import com.ga5000.api.ecommerce.application.usecases.category.dto.CategoryResponseDTO;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductResponseDTO;
import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;

public class Mapper {
    public static ProductResponseDTO productToProductResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getProductId(),
                product.getProductName(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getRemainingProductQuantity(),
                product.getProductImage(),
                product.getCategories().stream().map(Mapper::categoryToCategoryResponseDTO).toList(),
                product.isAvailable()
        );
    }

    public static CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
        return new CategoryResponseDTO(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }
}
