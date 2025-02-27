package com.ga5000.api.ecommerce.exception.product;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProductAlreadyExistsException extends BaseException {
    public ProductAlreadyExistsException() {
        super("This product already exists", HttpStatus.BAD_REQUEST);
    }
}
