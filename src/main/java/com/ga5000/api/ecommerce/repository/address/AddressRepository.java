package com.ga5000.api.ecommerce.repository.address;

import com.ga5000.api.ecommerce.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

    Address findByZipCode(String zipCode);

    @Query("SELECT a FROM Address a JOIN a.users u WHERE u.email = :email")
    Set<Address> findAddressesByEmail(String email);

    @Modifying
    @Query(value = "INSERT INTO user_addresses (user_id, address_id) "
            + "SELECT u.user_id, :addressId FROM users u WHERE u.email = :email", nativeQuery = true)
    void associateAddressWithUser(UUID addressId, String email);

    @Modifying
    @Query(value = "DELETE FROM user_addresses " +
            "WHERE user_id = (SELECT user_id FROM users WHERE email = :email) " +
            "AND address_id = :addressId", nativeQuery = true)
    void dissociateAddressFromUser(UUID addressId, String email);

    @Query(value = "SELECT COUNT(*) FROM user_addresses WHERE address_id = :addressId", nativeQuery = true)
    int countUsersAssociatedWithAddress(UUID addressId);
}
