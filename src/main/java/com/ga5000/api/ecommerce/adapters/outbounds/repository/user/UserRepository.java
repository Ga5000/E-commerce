package com.ga5000.api.ecommerce.adapters.outbounds.repository.user;

import com.ga5000.api.ecommerce.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}

