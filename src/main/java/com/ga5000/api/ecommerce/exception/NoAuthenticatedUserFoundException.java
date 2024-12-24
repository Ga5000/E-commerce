package com.ga5000.api.ecommerce.exception;

public class NoAuthenticatedUserFoundException extends RuntimeException {
    public NoAuthenticatedUserFoundException(String message) {
        super(message);
    }
}
