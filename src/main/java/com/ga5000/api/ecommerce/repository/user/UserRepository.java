package com.ga5000.api.ecommerce.repository.user;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.domain.user.customUserDetails.CustomUserDetails;
import com.ga5000.api.ecommerce.dto.user.UserInfoResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query("SELECT new com.ga5000.api.ecommerce.domain.user.customUserDetails.CustomUserDetails" +
            "(u.email, u.password, u.role) " +
            "FROM User u WHERE u.email = :email")
    CustomUserDetails findCustomUserDetailsByEmail(String email);

    @Query("SELECT new com.ga5000.api.ecommerce.dto.user.UserInfoResponse" +
            "( CONCAT(u.firstName, ' ', u.lastName), u.email, u.createdAt, SIZE(u.orders)) " +
            "FROM User u WHERE u.email = :email")
    UserInfoResponse findUserInfoByEmail(String email);

    boolean existsByEmail(String email);

    User findByEmail(String authenticatedUserEmail);
}

