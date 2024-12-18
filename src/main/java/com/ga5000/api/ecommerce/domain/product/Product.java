package com.ga5000.api.ecommerce.domain.product;

import com.ga5000.api.ecommerce.domain.category.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O nome não pode estar em branco")
    private String name = ""; // default = blank

    @Column(nullable = false)
    @Lob
    @NotBlank(message = "A descrição não pode estar em branco")
    private String description = ""; // default = blank

    @Column(nullable = false)
    @Positive(message = "O preço deve ser maior que zero")
    private double price = 0.0;

    @Column(nullable = false)
    @PositiveOrZero(message = "O estoque não pode ser negativo")
    private int stockQuantity = 0;

    @Column(nullable = false)
    @NotNull(message = "Um produto deve ter pelo menos uma imagem")
    @URL
    @ElementCollection
    private List<String> images = new ArrayList<>(); // urls

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // auto-generated

    @OneToMany(mappedBy = "product")
    @Min(value = 1, message = "Um produto deve ter pelo menos uma categoria")
    private Set<Category> categories = new HashSet<>();

    public Product() {
    }

    public Product(UUID productId, String name, String description, double price, int stockQuantity,
                   List<String> images, LocalDateTime createdAt, Set<Category> categories) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.images = images;
        this.createdAt = createdAt;
        this.categories = categories;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
