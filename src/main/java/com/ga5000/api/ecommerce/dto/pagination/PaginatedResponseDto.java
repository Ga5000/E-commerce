package com.ga5000.api.ecommerce.dto.pagination;

import java.util.List;

public record PaginatedResponseDto<T>(List<T> content, int totalPages, long totalElements, int pageNumber) {
}
