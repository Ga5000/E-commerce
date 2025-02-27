package com.ga5000.api.ecommerce.repository.product.specification;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.product.search.SearchProductParams;
import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Join;


public class ProductSpecification {
    public static Specification<Product> filterByParams(SearchProductParams params) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (params.name() != null && !params.name().isEmpty()) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%%" + params.name().toLowerCase() + "%%"));
            }

            if (params.minPrice() > 0) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThanOrEqualTo(root.get("price"), params.minPrice()));
            }

            if (params.maxPrice() > 0) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.lessThanOrEqualTo(root.get("price"), params.maxPrice()));
            }

            if (params.available()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThan(root.get("stock"), 0));
            }

            if (params.hasDiscount()) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.greaterThan(root.get("discount"), 0));
            }

            if (params.categories() != null && !params.categories().isEmpty()) {
                Join<Product, Category> categoryJoin = root.join("categories");
                predicates = criteriaBuilder.and(predicates, categoryJoin.in(params.categories()));
            }

            return predicates;
        };
    }
}
