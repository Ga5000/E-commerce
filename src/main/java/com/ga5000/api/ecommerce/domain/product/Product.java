package com.ga5000.api.ecommerce.domain.product;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.image.Image;
import com.ga5000.api.ecommerce.domain.item.Item;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @Column(length = 120, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    private double price;

    private int stock;

    private int discount; // %

    private LocalDate createdAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Item item;

    public Product(UUID productId, String name, String description, double price, int stock,
                   int discount, List<Image> images, List<Category> categories) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
        this.createdAt = LocalDate.now();
        this.images = images;
        this.categories = categories;
    }

    public Product(String name, String description, double price, int stock, int discount, List<Image> images, List<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.discount = discount;
        this.createdAt = LocalDate.now();
        this.images = images;
        this.categories = categories;
    }

    public Product(){
        this.createdAt = LocalDate.now();
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}