package com.ga5000.api.ecommerce.dto.category;
import jakarta.validation.constraints.NotEmpty;

/**
 * DTO used for handling requests related to category creation and updates.
 * <p>
 * This record is utilized in API endpoints that require category-related data,
 * ensuring validation for required fields.
 * </p>
 *
 * @param name The name of the category. This field is required.
 */
public record CategoryRequest(@NotEmpty(message = "A category name is required") String name) {
}
