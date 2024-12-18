package com.ga5000.api.ecommerce.dto.product;

import com.ga5000.api.ecommerce.domain.category.Category;

import java.util.List;

public record ProductSearchFilterDto(String name, int minPrice, int maxPrice, boolean inStock, List<Category> categories) {
}
