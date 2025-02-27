package com.ga5000.api.ecommerce.dto.product;

import com.ga5000.api.ecommerce.dto.category.CategoryResponse;
import com.ga5000.api.ecommerce.dto.image.ImageResponse;

import java.util.List;
import java.util.UUID;

public record ProductResponseInfo(UUID productId, String name, String description, double price, int stock, int discount, List<CategoryResponse> categories,
                                  List<ImageResponse> images) {
}
