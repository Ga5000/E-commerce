package com.ga5000.api.ecommerce.exception.address;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends BaseException {
    public AddressNotFoundException() {
        super("Address not found", HttpStatus.NOT_FOUND);
    }
}
