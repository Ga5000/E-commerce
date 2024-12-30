package com.ga5000.api.ecommerce.service.address;

import com.ga5000.api.ecommerce.dto.address.AddressRequestDto;
import com.ga5000.api.ecommerce.dto.address.AddressResponseDto;

import java.util.List;
import java.util.UUID;

public interface IAddressService {
    void addAddress(AddressRequestDto addressRequestDto);
    void updateAddress(UUID addressId, AddressRequestDto addressRequestDto);
    void deleteAddress(UUID addressId);
    List<AddressResponseDto> getUserAddresses();
}
