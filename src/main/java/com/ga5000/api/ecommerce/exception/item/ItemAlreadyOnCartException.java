package com.ga5000.api.ecommerce.exception.item;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ItemAlreadyOnCartException extends BaseException {
    public ItemAlreadyOnCartException() {
        super("This product is already on the cart", HttpStatus.BAD_REQUEST);
    }
}
