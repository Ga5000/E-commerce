package com.ga5000.api.ecommerce.repository.address;

import com.ga5000.api.ecommerce.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
    Address findByPostalCode(String postalCode);
}
