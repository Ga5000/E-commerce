package com.ga5000.api.ecommerce.service.auth;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.exception.NoAuthenticatedUserFoundException;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import com.ga5000.api.ecommerce.utils.exceptions.Message;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() throws NoAuthenticatedUserFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String email = ((UserDetails) principal).getUsername();
            return validadeAndReturnUser(email);
        }
        throw new NoAuthenticatedUserFoundException(Message.AuthMessage.NO_AUTHENTICATED_USER_FOUND.name());
    }

    private User validadeAndReturnUser(String email) throws EntityNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(Message.UserMessage.USER_NOT_FOUND.name()));
    }
}
