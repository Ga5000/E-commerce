package com.ga5000.api.ecommerce.application.usecases.product.dto;

import com.ga5000.api.ecommerce.domain.category.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.Product}
 */
public record ProductFilterDTO(@NotBlank String name, @NotNull @Size(min = 1) List<Category> categories, Boolean available,
                               Double minPrice, Double maxPrice) implements Serializable {
}