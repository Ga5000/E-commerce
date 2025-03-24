package com.ga5000.api.ecommerce.exceptions.entity;

import com.ga5000.api.ecommerce.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EntityAlreadyExistsException extends BaseException {
    public EntityAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
