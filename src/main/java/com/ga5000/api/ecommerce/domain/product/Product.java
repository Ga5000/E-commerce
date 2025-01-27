package com.ga5000.api.ecommerce.domain.product;

import com.ga5000.api.ecommerce.domain.product.category.Category;
import com.ga5000.api.ecommerce.domain.product.image.Image;
import com.ga5000.api.ecommerce.domain.review.Review;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id", nullable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID productId;


    @Column(name = "name", nullable = false, unique = true, length = 100)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "description", nullable = false, length = 500)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String description;

    @Column(name = "price", nullable = false)
    @JdbcTypeCode(SqlTypes.DOUBLE)
    @Positive(message = "Price must be greater than 0")
    private double price; // in reais

    @Column(name = "stock", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    @PositiveOrZero(message = "Stock must be greater than or equal to 0")
    private int stock; // in units

    @Column(name = "discount")
    @JdbcTypeCode(SqlTypes.INTEGER)
    @PositiveOrZero(message = "Discount must be greater or equal to 0")
    private int discount; // in percentage

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private Set<Category> categories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    public Product(UUID productId, String name, String description, double price, int stock, int discount,
                   List<Image> images, Set<Category> categories, List<Review> reviews) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
        this.images = images;
        this.categories = categories;
        this.reviews = reviews;
    }

    public Product() {
    }

    public Product(String name, String description, double price, int stock, int discount, List<Image> images,
                   Set<Category> categories, List<Review> reviews) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
        this.images = images;
        this.categories = categories;
        this.reviews = reviews;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
