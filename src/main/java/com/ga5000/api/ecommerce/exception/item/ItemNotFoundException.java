package com.ga5000.api.ecommerce.exception.item;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends BaseException {
    public ItemNotFoundException() {
        super("Item not found", HttpStatus.NOT_FOUND);
    }
}
