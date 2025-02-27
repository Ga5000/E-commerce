package com.ga5000.api.ecommerce.dto.image;

import com.ga5000.api.ecommerce.domain.product.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public record ImageRequest(Product product, @NotNull(message = "File is required") MultipartFile file) {
}
