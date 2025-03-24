package com.ga5000.api.ecommerce.dto.category;
import java.util.UUID;

/**
 * DTO used for presenting category data to the user.
 * <p>
 * This record is returned in API responses when fetching category information.
 * It contains the category's unique identifier and name.
 * </p>
 *
 * @param id   The unique identifier of the category.
 * @param name The name of the category.
 */
public record CategoryResponse(UUID id, String name) {
}

