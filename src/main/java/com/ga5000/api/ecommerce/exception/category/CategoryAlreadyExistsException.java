package com.ga5000.api.ecommerce.exception.category;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CategoryAlreadyExistsException extends BaseException {
    public CategoryAlreadyExistsException() {
        super("This category already exists", HttpStatus.BAD_REQUEST);
    }
}
