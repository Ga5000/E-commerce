package com.ga5000.api.ecommerce.domain.product;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.comment.Comment;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @Column(nullable = false)
    @Lob
    @NotBlank(message = "A descrição não pode estar em branco")
    private String description;

    @Column(nullable = false)
    @Positive(message = "O preço deve ser maior que zero")
    private double price;

    @Column(nullable = false)
    @PositiveOrZero(message = "O estoque não pode ser negativo")
    private int inventory;

    @ElementCollection
    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "image_url", nullable = false)
    @URL(message = "A URL da imagem deve ser válida")
    private List<String> images = new ArrayList<>();

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToMany
    @JoinTable(
            name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @NotEmpty(message = "Um produto deve ter pelo menos uma categoria")
    private Set<Category> categories = new HashSet<>();


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description, double price, int inventory, List<String> images,
                   Set<Category> categories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.inventory = inventory;
        this.images = images;
        this.categories = categories;
    }

    public UUID getProductId() {
        return productId;
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

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public List<String> getImages() {
        return Collections.unmodifiableList(images);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public double getAverageRating() {
        return comments.stream()
                .mapToInt(Comment::getRating)
                .average()
                .orElse(0.0);
    }
}
