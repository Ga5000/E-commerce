package com.ga5000.api.product.domain.model.category;

import com.ga5000.api.product.domain.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Product> products = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public boolean hasProducts() {
        return products.isEmpty();
    }

    public void addProduct(Product product) {
        products.add(product);
        product.addCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.removeCategory(this);
    }

}
