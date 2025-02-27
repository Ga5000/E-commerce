package com.ga5000.api.ecommerce.exception.category;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class NoValueChangeException extends BaseException {
    public NoValueChangeException() {
        super("The new value is the same as the current value. No update needed.", HttpStatus.BAD_REQUEST);
    }
}
