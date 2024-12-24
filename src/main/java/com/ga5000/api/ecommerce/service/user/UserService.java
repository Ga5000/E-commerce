package com.ga5000.api.ecommerce.service.user;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.exception.NoAuthenticatedUserFoundException;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
}
