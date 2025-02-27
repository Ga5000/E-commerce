package com.ga5000.api.ecommerce.exception.order;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class OrderNotFound extends BaseException {
    public OrderNotFound() {
        super("Order not found", HttpStatus.NOT_FOUND);
    }
}
