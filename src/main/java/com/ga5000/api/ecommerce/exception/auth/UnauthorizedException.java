package com.ga5000.api.ecommerce.exception.auth;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException() {
        super("User is not authenticated", HttpStatus.UNAUTHORIZED);
    }
}
