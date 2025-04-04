package com.ga5000.api.ecommerce.domain.repository.user;

import com.ga5000.api.ecommerce.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
