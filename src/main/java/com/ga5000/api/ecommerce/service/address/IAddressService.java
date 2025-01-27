package com.ga5000.api.ecommerce.service.address;

import com.ga5000.api.ecommerce.dto.address.AddressRequestDto;
import com.ga5000.api.ecommerce.dto.address.AddressResponseDto;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface IAddressService {
    void createAddress(AddressRequestDto request) throws EntityExistsException;
    void updateAddress(UUID addressId, AddressRequestDto request) throws EntityExistsException;
    void deleteAddress(UUID addressId);

    List<AddressResponseDto> getAddresses();
    AddressResponseDto getAddress(UUID addressId);
}
