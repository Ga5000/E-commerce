package com.ga5000.api.ecommerce.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.product.Product}
 */
public record ProductRequestDto(@NotBlank(message = "O nome não pode estar em branco") String name,
                                @NotBlank(message = "A descrição não pode estar em branco") String description,
                                @Positive(message = "O preço deve ser maior que zero") double price,
                                @PositiveOrZero(message = "O estoque não pode ser negativo") int inventory,
                                @NotBlank @Min(value = 1, message = "Um produto deve ter pelo menos uma categoria") List<UUID> categoriesIds,
                                List<MultipartFile> files) implements Serializable {
}