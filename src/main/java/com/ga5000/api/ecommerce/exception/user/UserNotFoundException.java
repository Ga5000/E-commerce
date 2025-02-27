package com.ga5000.api.ecommerce.exception.user;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
