package com.ga5000.api.ecommerce.dto.product;

import com.ga5000.api.ecommerce.domain.category.Category;
import com.ga5000.api.ecommerce.dto.category.CategoryResponseDto;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.Product}
 */
public record ProductResponseDto(UUID productId, @NotBlank(message = "O nome não pode estar em branco") String name,
                                 @NotBlank(message = "A descrição não pode estar em branco") String description,
                                 @Positive(message = "O preço deve ser maior que zero") double price,
                                 @PositiveOrZero(message = "O estoque não pode ser negativo") int stockQuantity,
                                 @NotNull(message = "Um produto deve ter pelo menos uma imagem") @URL List<String> images,
                                 @Min(value = 1, message = "Um produto deve ter pelo menos uma categoria") Set<CategoryResponseDto> categories)
        implements Serializable {
}