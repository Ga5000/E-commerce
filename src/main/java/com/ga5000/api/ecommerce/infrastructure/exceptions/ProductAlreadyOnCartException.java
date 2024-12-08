package com.ga5000.api.ecommerce.infrastructure.exceptions;

public class ProductAlreadyOnCartException extends RuntimeException {
    public ProductAlreadyOnCartException(String message) {
        super(message);
    }
}
