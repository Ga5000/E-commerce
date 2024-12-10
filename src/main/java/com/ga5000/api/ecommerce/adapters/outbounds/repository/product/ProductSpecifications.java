package com.ga5000.api.ecommerce.adapters.outbounds.repository.product;
import com.ga5000.api.ecommerce.application.usecases.product.dto.ProductFilterDTO;
import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecifications {
    public static Specification<Product> withFilters(ProductFilterDTO productFilter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (productFilter.categories() != null && !productFilter.categories().isEmpty()) {
                CriteriaBuilder.In<Object> inClause = criteriaBuilder.in(root.get("categories"));
                productFilter.categories().forEach(inClause::value);
                predicates.add(inClause);
            }

            if (productFilter.name() != null && !productFilter.name().isBlank()) {
                predicates.add(criteriaBuilder.like(root.get("productName"), "%" + productFilter.name() + "%"));
            }


            if (productFilter.available() != null) {
                predicates.add(criteriaBuilder.equal(root.get("available"), productFilter.available()));
            }


            if (productFilter.minPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productPrice"), productFilter.minPrice()));
            }
            if (productFilter.maxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("productPrice"), productFilter.maxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
