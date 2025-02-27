package com.ga5000.api.ecommerce.dto.address;

import jakarta.validation.constraints.NotBlank;

public record AddressRequest(
        @NotBlank(message = "Street is required") String street,
        @NotBlank(message = "Number is required") String number,
        @NotBlank(message = "Zip code is required") String zipCode,
        String complement
) {
}
