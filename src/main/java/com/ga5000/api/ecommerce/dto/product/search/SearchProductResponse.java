package com.ga5000.api.ecommerce.dto.product.search;

import com.ga5000.api.ecommerce.dto.image.ImageResponse;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

public class SearchProductResponse extends RepresentationModel<SearchProductResponse> {
    private final UUID productId;
    private final String name;
    private final ImageResponse image;
    private final double price;
    private final int discount;

    public SearchProductResponse(UUID productId, String name, ImageResponse image, double price, int discount) {
        this.productId = productId;
        this.name = name;
        this.image = image;
        this.price = price;
        this.discount = discount;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public ImageResponse getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }
}
