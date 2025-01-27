package com.ga5000.api.ecommerce.dto.page;

import org.springframework.data.domain.Sort;

public record PageRequestDto(int page, int size, Sort.Direction direction) {
}
