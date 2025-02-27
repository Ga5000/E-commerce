package com.ga5000.api.ecommerce.domain.category;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;

    @Column(unique = true, length = 120)
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();


    public Category(UUID categoryId, String name, List<Product> products) {
        this.categoryId = categoryId;
        this.name = name;
        this.products = products;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name,  List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Category(UUID categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }

    public Category() {
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  List<Product>  getProducts() {
        return products;
    }

    public void setProduct( List<Product> products) {
        this.products = products;
    }
}
