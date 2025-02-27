package com.ga5000.api.ecommerce.dto.address;

import java.util.UUID;

public record AddressResponse(UUID addressId, String address, String zipCode) {
}
