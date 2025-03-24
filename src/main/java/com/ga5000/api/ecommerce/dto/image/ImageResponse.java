package com.ga5000.api.ecommerce.dto.image;

import java.util.UUID;

/**
 * A DTO (Data Transfer Object) representing the response for an image.
 * This record encapsulates the image's unique identifier and its URL.
 *
 * @param id  the unique identifier of the image
 * @param url the URL where the image is hosted
 */
public record ImageResponse(UUID id, String url) {
}
