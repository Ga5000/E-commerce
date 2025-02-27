package com.ga5000.api.ecommerce.dto.product.search;

import com.ga5000.api.ecommerce.dto.category.CategoryResponse;

import java.util.List;

public record SearchProductParams(String name, Integer minPrice, Integer maxPrice, boolean available, boolean hasDiscount, List<CategoryResponse> categories) {
}
