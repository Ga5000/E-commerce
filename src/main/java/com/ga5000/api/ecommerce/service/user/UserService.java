package com.ga5000.api.ecommerce.service.user;

import com.ga5000.api.ecommerce.dto.user.UserAccountDetailsDto;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public UserAccountDetailsDto getUserAccountDetails() {
        return null;
    }
}
