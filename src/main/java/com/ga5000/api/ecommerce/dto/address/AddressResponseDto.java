package com.ga5000.api.ecommerce.dto.address;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.ga5000.api.ecommerce.domain.address.Address}
 */
public record AddressResponseDto(UUID addressId, @NotBlank(message = "Postal code must not be blank") String postalCode,
                                 @NotBlank(message = "Street must not be blank") String street,
                                 @NotBlank(message = "Number must not be blank") String number, String complement,
                                 @NotBlank(message = "Neighborhood must not be blank") String neighborhood,
                                 @NotBlank(message = "City must not be blank") String city,
                                 @NotBlank(message = "State must not be blank") String state,
                                 @NotBlank(message = "Region must not be blank") String region) implements Serializable {
}