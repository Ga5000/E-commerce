package com.ga5000.api.ecommerce.utils;


import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.domain.product.ProductSearchDto;
import com.ga5000.api.ecommerce.domain.product.category.Category;
import com.ga5000.api.ecommerce.domain.product.image.Image;
import com.ga5000.api.ecommerce.dto.address.AddressResponseDto;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import com.ga5000.api.ecommerce.dto.image.ImageResponseDto;
import com.ga5000.api.ecommerce.dto.product.ProductResponseDto;

import java.util.stream.Collectors;

public class Mapper {
    private Mapper(){}

    public static AddressResponseDto toAddressResponse(Address address) {

        return new AddressResponseDto(
                address.getAddressId(),
                address.getPostalCode(),
                address.getStreet(),
                address.getNumber(),
                address.getComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getRegion()
        );
    }

    public static CategoryResponseDto toCategoryResponseDto(Category category) {
        return new CategoryResponseDto(category.getCategoryId(), category.getName());
    }

    public static ImageResponseDto toImageResponseDto(Image image) {
        return new ImageResponseDto(image.getImageId(), image.getUrl());
    }

    public static ProductResponseDto toProductResponseDto(Product product) {
        return new ProductResponseDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getDiscount(),
                product.getImages().stream().map(Mapper::toImageResponseDto).toList(),
                product.getCategories().stream().map(Mapper::toCategoryResponseDto).collect(Collectors.toSet())
        );
    }

    public static ProductSearchDto toProductSearchDto(Product product) {
        return new ProductSearchDto(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getDiscount(),
                product.getImages().stream().map(Mapper::toImageResponseDto).toList()
        );
    }

}
