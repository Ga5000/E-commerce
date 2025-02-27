package com.ga5000.api.ecommerce.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record ResetPasswordRequest(@NotBlank(message = "Password is required") String newPassword, String token) {
}
