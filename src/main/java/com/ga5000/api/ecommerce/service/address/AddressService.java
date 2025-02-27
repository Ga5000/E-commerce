package com.ga5000.api.ecommerce.service.address;

import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.dto.address.AddressRequest;
import com.ga5000.api.ecommerce.dto.address.AddressResponse;
import com.ga5000.api.ecommerce.exception.address.AddressAlreadyExistsException;
import com.ga5000.api.ecommerce.exception.address.AddressNotFoundException;
import com.ga5000.api.ecommerce.repository.address.AddressRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import com.ga5000.api.ecommerce.utils.DtoMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.ga5000.api.ecommerce.utils.RepositoryUtils.findByIdOrThrow;

@Service
public class AddressService implements IAddressService{

    private final AddressRepository addressRepository;
    private final AuthService authService;
    private final DtoMapper dtoMapper;

    public AddressService(AddressRepository addressRepository, AuthService authService, DtoMapper dtoMapper) {
        this.addressRepository = addressRepository;
        this.authService = authService;
        this.dtoMapper = dtoMapper;
    }

    @Transactional
    @Override
    public void createAddress(AddressRequest addressRequest) {
        validateAddress(addressRequest.zipCode());
        Address newAddress = new Address();

        BeanUtils.copyProperties(addressRequest, newAddress);
        saveAddress(newAddress);
        addressRepository.associateAddressWithUser(newAddress.getAddressId(), authService.getAuthenticatedUserEmail());

    }

    @Transactional
    @Override
    public void removeAddress(UUID addressId) {
        Address address = findByIdOrThrow(addressId, addressRepository, AddressNotFoundException::new);
        addressRepository.dissociateAddressFromUser(addressId, authService.getAuthenticatedUserEmail());

        if(addressRepository.countUsersAssociatedWithAddress(addressId) == 0) {
            addressRepository.delete(address);
        }else{
            saveAddress(address);
        }
    }

    @Override
    public Set<AddressResponse> getAddresses() {
        return addressRepository.findAddressesByEmail(authService.getAuthenticatedUserEmail())
                .stream().map(dtoMapper::toAddressResponse).collect(Collectors.toSet());
    }

    private void validateAddress(String zipCode) throws AddressAlreadyExistsException {
        Address address = addressRepository.findByZipCode(zipCode);
        if(address != null){
            throw new AddressAlreadyExistsException();
        }
    }

    private void saveAddress(Address address){
        addressRepository.save(address);
    }


}
