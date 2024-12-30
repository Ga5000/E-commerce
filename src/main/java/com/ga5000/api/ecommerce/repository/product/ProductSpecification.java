package com.ga5000.api.ecommerce.repository.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.product.ProductSearchFilterDto;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ProductSpecification {


    public static Specification<Product> fromFilter(ProductSearchFilterDto filter) {
        return Specification.where(nameLike(filter.name()))
                .and(priceRange(filter.minPrice(), filter.maxPrice()))
                .and(hasCategories(filter.categoryNames()))
                .and(isInStock(filter.available()));
    }


    private static Specification<Product> nameLike(String name) {
        return (root, query, cb) -> {
            if (name == null || name.trim().isEmpty()) {
                return cb.conjunction();
            }
            return cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }


    private static Specification<Product> priceRange(int minPrice, int maxPrice) {
        return (root, query, cb) -> {
            if (minPrice <= 0 && maxPrice <= 0) {
                return cb.conjunction();
            }
            if (minPrice > 0 && maxPrice > 0) {
                return cb.between(root.get("price"), minPrice, maxPrice);
            }
            if (minPrice > 0) {
                return cb.greaterThanOrEqualTo(root.get("price"), minPrice);
            }
            return cb.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    private static Specification<Product> hasCategories(List<String> categoryNames) {
        return (root, query, cb) -> {
            if (categoryNames == null || categoryNames.isEmpty()) {
                return cb.conjunction();
            }
            return root.get("category").get("categoryName").in(categoryNames);
        };
    }

    private static Specification<Product> isInStock(boolean available) {
        return (root, query, cb) -> {
            if (!available) {
                return cb.conjunction();
            }
            return cb.greaterThan(root.get("inventory"), 0);
        };
    }
}
