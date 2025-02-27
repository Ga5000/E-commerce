package com.ga5000.api.ecommerce.service.auth;

import com.ga5000.api.ecommerce.domain.user.User;
import com.ga5000.api.ecommerce.dto.auth.LoginRequest;
import com.ga5000.api.ecommerce.dto.auth.RegisterRequest;
import com.ga5000.api.ecommerce.dto.auth.ResetPasswordRequest;
import com.ga5000.api.ecommerce.exception.user.UserNotFoundException;

public interface IAuthService {
    String login(LoginRequest loginRequest);
    void register(RegisterRequest registerRequest);
    void recoverPassword(String email) throws UserNotFoundException;

    void resetPassword(ResetPasswordRequest request);

    String getAuthenticatedUserEmail();
}
