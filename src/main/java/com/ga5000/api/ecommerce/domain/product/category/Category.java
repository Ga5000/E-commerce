package com.ga5000.api.ecommerce.domain.product.category;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "category_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID categoryId;

    @Column(name = "name", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotBlank(message = "Name is mandatory")
    @NotNull(message = "Name is mandatory")
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, referencedColumnName = "productId")
    private Product product;

    public Category(UUID categoryId, String name, Product product) {
        this.categoryId = categoryId;
        this.name = name;
        this.product = product;
    }

    public Category() {
    }

    public Category(String name, Product product) {
        this.name = name;
        this.product = product;
    }

    public Category(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}
