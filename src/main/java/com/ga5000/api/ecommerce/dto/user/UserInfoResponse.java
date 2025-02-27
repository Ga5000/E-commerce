package com.ga5000.api.ecommerce.dto.user;


import java.time.LocalDate;

public record UserInfoResponse(String fullName, String email, LocalDate createdAt, int totalOrders) {
}
