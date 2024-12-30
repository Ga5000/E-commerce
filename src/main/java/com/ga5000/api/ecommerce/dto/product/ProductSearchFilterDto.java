package com.ga5000.api.ecommerce.dto.product;

import java.util.List;

public record ProductSearchFilterDto(String name, int minPrice, int maxPrice, List<String> categoryNames, boolean available) {
}
