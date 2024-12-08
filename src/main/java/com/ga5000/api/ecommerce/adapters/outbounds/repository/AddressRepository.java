package com.ga5000.api.ecommerce.adapters.outbounds.repository;

import com.ga5000.api.ecommerce.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
