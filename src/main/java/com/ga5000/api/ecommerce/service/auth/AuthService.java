package com.ga5000.api.ecommerce.service.auth;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.domain.user.customUserDetails.CustomUserDetails;
import com.ga5000.api.ecommerce.dto.auth.LoginRequest;
import com.ga5000.api.ecommerce.dto.auth.RegisterRequest;
import com.ga5000.api.ecommerce.dto.auth.ResetPasswordRequest;
import com.ga5000.api.ecommerce.exception.auth.EmailAlreadyInUseException;
import com.ga5000.api.ecommerce.exception.auth.InvalidCredentialsException;
import com.ga5000.api.ecommerce.exception.auth.UnauthorizedException;
import com.ga5000.api.ecommerce.exception.user.UserNotFoundException;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.service.auth.token.TokenService;
import com.ga5000.api.ecommerce.service.email.EmailService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        CustomUserDetails user = userRepository.findCustomUserDetailsByEmail(loginRequest.email());
        if (user == null || !passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        return tokenService.generateToken(user);
    }

    @Transactional
    @Override
    public void register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.email())) {
            throw new EmailAlreadyInUseException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
        User newUser = new User(registerRequest.firstName(), registerRequest.lastName(), registerRequest.email(), encryptedPassword);

        userRepository.save(newUser);
    }

    @Override
    public void recoverPassword(String email) throws UserNotFoundException {

    }

    @Override
    public void resetPassword(ResetPasswordRequest request) {

    }


    @Override
    public String getAuthenticatedUserEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new UnauthorizedException();
        }

        return ((CustomUserDetails) authentication.getPrincipal()).getUsername();
    }

}
