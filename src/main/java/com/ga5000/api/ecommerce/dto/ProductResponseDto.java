package com.ga5000.api.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
                                 @PositiveOrZero(message = "O estoque não pode ser negativo") int inventory,
                                 @URL(message = "A URL da imagem deve ser válida") List<String> images,
                                 Set<CategoryResponseDto> categories) implements Serializable {
}