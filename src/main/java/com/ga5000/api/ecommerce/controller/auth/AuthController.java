package com.ga5000.api.ecommerce.controller.auth;

import com.ga5000.api.ecommerce.dto.auth.LoginRequest;
import com.ga5000.api.ecommerce.dto.auth.RegisterRequest;
import com.ga5000.api.ecommerce.dto.auth.ResetPasswordRequest;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = authService.login(loginRequest);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("success", "Registered successfully"));

    }

    @PostMapping("/recover-password")
    public ResponseEntity<Map<String, String>> recoverPassword(@RequestParam String email) {
        authService.recoverPassword(email);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("success", "Password recovery email sent"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody String newPassword, @RequestParam String token) {
        authService.resetPassword(new ResetPasswordRequest(newPassword, token));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Collections.singletonMap("success", "Password reset successfully"));
    }
}
