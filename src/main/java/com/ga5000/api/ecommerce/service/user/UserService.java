package com.ga5000.api.ecommerce.service.user;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.dto.user.UserInfoResponse;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.service.auth.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service

public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final AuthService authService;

    public UserService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Transactional
    @Override
    public UserInfoResponse getUserInfo() {
        return userRepository.findUserInfoByEmail(authService.getAuthenticatedUserEmail());
    }
}
