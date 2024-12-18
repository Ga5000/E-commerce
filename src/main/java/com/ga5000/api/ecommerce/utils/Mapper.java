package com.ga5000.api.ecommerce.utils;

import com.ga5000.api.ecommerce.domain.cart.Cart;
import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.cart.CartResponseDto;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;

import java.util.stream.Collectors;

public class Mapper {
    public static ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStockQuantity(),
                product.getImages(),
                product.getCategories().stream().map(Mapper::toCategoryResponseDto).collect(Collectors.toSet())
        );
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(
                category.getCategoryId(),
                category.getCategoryName()
        );
    }

    public static CartResponseDto toCartResponseDto(Cart cart) {
        return new CartResponseDto(
                cart.getCartId(),
                cart.getItems(),
                cart.getTotalPrice()
        );
    }

}
