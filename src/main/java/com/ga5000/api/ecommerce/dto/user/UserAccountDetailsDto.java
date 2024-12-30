package com.ga5000.api.ecommerce.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.user.User}
 */
public record UserAccountDetailsDto(UUID userId,
                                    @NotBlank(message = "O primeiro nome não pode estar em branco") String firstName,
                                    @NotBlank(message = "O sobrenome não pode estar em branco") String lastName,
                                    @Email(message = "O e-mail deve ser válido") String email, LocalDateTime createdAt,
                                    LocalDateTime updatedAt) implements Serializable {
}