package com.ga5000.api.ecommerce.utils;


import com.ga5000.api.ecommerce.domain.user.client.Client;
import com.ga5000.api.ecommerce.repository.user.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

import static com.ga5000.api.ecommerce.utils.ExceptionMessage.INVALID_AUTHENTICATION;
import static com.ga5000.api.ecommerce.utils.ExceptionMessage.NO_AUTHENTICATION;

public class AuthUtil {
    private AuthUtil() {}

    private static UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException(NO_AUTHENTICATION);
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return UUID.fromString(((UserDetails) principal).getUsername());
        }

        throw new SecurityException(INVALID_AUTHENTICATION);
    }

    public static Client getCurrentClient(UserRepository userRepository) {
        UUID userId = getCurrentUserId();
        return userRepository.findById(userId)
                .filter(Client.class::isInstance)
                .map(Client.class::cast)
                .orElseThrow(() -> new SecurityException(INVALID_AUTHENTICATION));
    }
}
