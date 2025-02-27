package com.ga5000.api.ecommerce.exception.auth;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyInUseException extends BaseException {
    public EmailAlreadyInUseException() {
        super("This e-mail is already in use, try another", HttpStatus.BAD_REQUEST);
    }
}
