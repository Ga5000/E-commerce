package com.ga5000.api.ecommerce.exception.address;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AddressAlreadyExistsException extends BaseException {
    public AddressAlreadyExistsException() {
        super("You already have registered this address", HttpStatus.BAD_REQUEST);
    }
}
