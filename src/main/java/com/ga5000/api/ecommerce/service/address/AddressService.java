package com.ga5000.api.ecommerce.service.address;

import com.ga5000.api.ecommerce.domain.address.Address;
import com.ga5000.api.ecommerce.domain.user.client.Client;
import com.ga5000.api.ecommerce.dto.address.AddressRequestDto;
import com.ga5000.api.ecommerce.dto.address.AddressResponseDto;
import com.ga5000.api.ecommerce.repository.address.AddressRepository;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.utils.Mapper;
import jakarta.persistence.EntityExistsException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.AuthUtil.getCurrentClient;
import static com.ga5000.api.ecommerce.utils.ExceptionMessage.ADDRESS_EXISTS;
import static com.ga5000.api.ecommerce.utils.ExceptionMessage.ADDRESS_NOT_FOUND;
import static com.ga5000.api.ecommerce.utils.Mapper.toAddressResponse;
import static com.ga5000.api.ecommerce.utils.RepositoryUtil.getById;
import static com.ga5000.api.ecommerce.utils.RequestUtil.mapRequest;
import static com.ga5000.api.ecommerce.utils.ValidationUtil.*;

@Service
public class AddressService implements IAddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void createAddress(AddressRequestDto request) throws EntityExistsException {
        var newAddress = new Address();
        checkEntityAvailability(request.postalCode(), addressRepository,
                addressRepository::findByPostalCode, ADDRESS_EXISTS);

        Client client = getCurrentClient(userRepository);
        mapRequest(request, newAddress);
        newAddress.setClient(client);

        saveAddress(newAddress);
    }

    @Override
    @Transactional
    public void updateAddress(UUID addressId, AddressRequestDto request) throws EntityExistsException {
        Address existingAddress = getById(addressId, addressRepository, "Address not found");

        if (!isSameValue(existingAddress.getPostalCode(), request.postalCode())) {
            checkEntityAvailability(request.postalCode(), addressRepository,
                    addressRepository::findByPostalCode, ADDRESS_EXISTS);
        }

        Client client = getCurrentClient(userRepository);
        validateOwnership(client, existingAddress, Address::getClient);

        mapRequest(request, existingAddress);
        saveAddress(existingAddress);
    }

    @Override
    @Transactional
    public void deleteAddress(UUID addressId) {
        Address existingAddress = getById(addressId, addressRepository, ADDRESS_NOT_FOUND);
        Client client = getCurrentClient(userRepository);
        validateOwnership(client, existingAddress, Address::getClient);
        addressRepository.delete(existingAddress);
    }

    @Override
    public List<AddressResponseDto> getAddresses() {
        return addressRepository.findAll(Sort.by(Sort.Order.asc("postalCode")))
                .stream()
                .map(Mapper::toAddressResponse)
                .toList();
    }

    @Override
    public AddressResponseDto getAddress(UUID addressId) {
        return toAddressResponse(getById(addressId, addressRepository, ADDRESS_NOT_FOUND));
    }

    private void saveAddress(Address address) {
        addressRepository.save(address);
    }


}
