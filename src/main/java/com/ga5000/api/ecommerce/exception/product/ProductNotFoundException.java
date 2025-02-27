package com.ga5000.api.ecommerce.exception.product;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException() {
        super("Product not found", HttpStatus.NOT_FOUND);
    }
}
