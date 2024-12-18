package com.ga5000.api.ecommerce.domain.product.productItem;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductItem {
    private String name;
    private double price;
    private String imageUrl;
    private int quantity;

    public ProductItem(String name, double price, String imageUrl, int quantity) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    public ProductItem() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
