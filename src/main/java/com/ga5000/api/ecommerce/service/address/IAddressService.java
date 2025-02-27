package com.ga5000.api.ecommerce.service.address;

import com.ga5000.api.ecommerce.dto.address.AddressRequest;
import com.ga5000.api.ecommerce.dto.address.AddressResponse;

import java.util.Set;
import java.util.UUID;

public interface IAddressService {
    void createAddress(AddressRequest addressRequest);
    void removeAddress(UUID addressId);

    Set<AddressResponse> getAddresses();
}
