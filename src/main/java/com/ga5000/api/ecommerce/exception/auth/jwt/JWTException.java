package com.ga5000.api.ecommerce.exception.auth.jwt;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class JWTException extends BaseException {
    public JWTException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
