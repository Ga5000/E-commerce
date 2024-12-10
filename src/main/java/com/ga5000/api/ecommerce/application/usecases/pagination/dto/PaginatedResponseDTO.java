package com.ga5000.api.ecommerce.application.usecases.pagination.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record PaginatedResponseDTO<T> (
        List<T> content,
        @Size(min = 1) int totalPages,
        long totalElements,
        @Size(min = 1) int currentPage){}
