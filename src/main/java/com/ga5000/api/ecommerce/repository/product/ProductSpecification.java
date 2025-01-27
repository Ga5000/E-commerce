package com.ga5000.api.ecommerce.repository.product;

import com.ga5000.api.ecommerce.domain.product.Product;
import com.ga5000.api.ecommerce.dto.product.SearchParams;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.Predicate;

public class ProductSpecification {
    public static Specification<Product> bySearchParams(SearchParams searchParams) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchParams != null) {

                if (searchParams.name() != null && !searchParams.name().isBlank()) {
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")),
                            "%" + searchParams.name().toLowerCase() + "%"
                    ));
                }

                if (searchParams.minPrice() > 0) {
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), searchParams.minPrice()));
                }

                if (searchParams.MaxPrice() > 0) {
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchParams.MaxPrice()));
                }

                if (searchParams.hasDiscount()) {
                    predicates.add(criteriaBuilder.notEqual(root.get("discount"), 0));
                }

                if (searchParams.inStock()) {
                    predicates.add(criteriaBuilder.greaterThan(root.get("stock"), 0));
                }

                if (searchParams.categories() != null && !searchParams.categories().isEmpty()) {
                    predicates.add(root.join("categories").in(searchParams.categories()));
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}