package com.ga5000.api.ecommerce.repository.product;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.product.ProductSearchFilterDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {

    // Builds a Specification based on the ProductSearchFilterDto
    public static Specification<Product> fromFilter(ProductSearchFilterDto filter) {
        return Specification.where(nameLike(filter.name()))
                .and(priceRange(filter.minPrice(), filter.maxPrice()))
                .and(hasCategories(filter.categories()))
                .and(isInStock(filter.inStock()));
    }

    // Filters products where 'name' matches the given pattern (case-insensitive).
    private static Specification<Product> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.trim().isEmpty()) {
                return cb.conjunction(); // No filter applied if the name is null or empty.
            }
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    // Filters products within a given price range [min, max].
    private static Specification<Product> priceRange(int minPrice, int maxPrice) {
        return (root, query, cb) -> {
            if (minPrice <= 0 && maxPrice <= 0) {
                return cb.conjunction(); // No filter applied if both are not set.
            }
            if (minPrice > 0 && maxPrice > 0) {
                return cb.between(root.get("price"), minPrice, maxPrice); // Both bounds specified.
            }
            if (minPrice > 0) {
                return cb.greaterThanOrEqualTo(root.get("price"), minPrice); // Only minimum specified.
            }
            return cb.lessThanOrEqualTo(root.get("price"), maxPrice); // Only maximum specified.
        };
    }

    // Filters products that belong to any of the given categories.
    private static Specification<Product> hasCategories(List<Category> categories) {
        return (root, query, cb) -> {
            if (categories == null || categories.isEmpty()) {
                return cb.conjunction(); // No filter applied if categories are null or empty.
            }
            return root.get("category").in(categories);
        };
    }

    // Filters products that are in stock.
    private static Specification<Product> isInStock(boolean inStock) {
        return (root, query, cb) -> {
            if (!inStock) {
                return cb.conjunction(); // No filter applied if inStock is false.
            }
            return cb.greaterThan(root.get("stockQuantity"), 0); // Filter by stock quantity.
        };
    }
}
