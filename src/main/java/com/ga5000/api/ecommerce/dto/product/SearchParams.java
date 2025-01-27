package com.ga5000.api.ecommerce.dto.product;

import com.ga5000.api.ecommerce.domain.product.category.Category;

import java.util.List;

public record SearchParams(String name, double minPrice, double MaxPrice, boolean hasDiscount,
                           boolean inStock, List<Category> categories) {
}
