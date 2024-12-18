package com.ga5000.api.ecommerce.domain.category;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;

    @Column(nullable = false)
    @NotBlank(message = "O nome da categoria não pode estar vazio")
    private String categoryName = ""; // default = blank

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;

    public Category(UUID categoryId, String categoryName, Product product) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.product = product;
    }

    public Category() {

    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}