package com.ga5000.api.ecommerce.exception.auth.token;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends BaseException {
    public TokenExpiredException() {
        super("Token expired", HttpStatus.UNAUTHORIZED);
    }
}
