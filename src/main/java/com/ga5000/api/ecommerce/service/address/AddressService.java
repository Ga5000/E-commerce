package com.ga5000.api.ecommerce.service.address;

import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.dto.address.AddressResponseDto;
import com.ga5000.api.ecommerce.dto.address.AddressRequestDto;
import com.ga5000.api.ecommerce.exception.UnauthorizedException;
import com.ga5000.api.ecommerce.repository.address.AddressRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import com.ga5000.api.ecommerce.utils.mapper.Mapper;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final AuthService authService;

    public AddressService(AddressRepository addressRepository, AuthService authService) {
        this.addressRepository = addressRepository;
        this.authService = authService;
    }


    @Override
    public void addAddress(AddressRequestDto addressRequestDto) throws EntityExistsException {
        var address = new Address();
        BeanUtils.copyProperties(addressRequestDto, address);
        address.setUser(authService.getCurrentUser());


        checkIfEqualExists(address);
        addressRepository.save(address);
    }

    @Override
    public void updateAddress(UUID addressId, AddressRequestDto addressRequestDto) {
        Address address = getById(addressId);
        compareIds(addressId);
        BeanUtils.copyProperties(addressRequestDto, address);

        checkIfEqualExists(address);
        addressRepository.save(address);
    }

    @Override
    public void deleteAddress(UUID addressId) {
        Address address = getById(addressId);
        compareIds(addressId);
        addressRepository.delete(address);
    }

    @Override
    public List<AddressResponseDto> getUserAddresses() {
        return authService.getCurrentUser()
                .getAddresses().stream().map(Mapper::toAddressResponseDto)
                .toList();
    }

    private Address getById(UUID addressId) throws EntityNotFoundException {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException(Message.AddressMessage.ADDRESS_NOT_FOUND.name()));
    }

    private void compareIds(UUID addressId) throws UnauthorizedException {
        if(!authService.getCurrentUser().getAddresses().contains(getById(addressId))) {
            throw new UnauthorizedException(Message.AuthMessage.UNAUTHORIZED.name());
        }
    }

    private void checkIfEqualExists(Address address) throws EntityExistsException {
        if(authService.getCurrentUser().getAddresses().contains(address)) {
            throw new EntityExistsException(Message.AddressMessage.ADDRESS_EXISTS.name());
        }
    }
}
