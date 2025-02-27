package com.ga5000.api.ecommerce.exception.auth.token;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class GenerateRecoveryTokenException extends BaseException {
    public GenerateRecoveryTokenException() {
        super("Error generating recovery token", HttpStatus.BAD_REQUEST);
    }
}
