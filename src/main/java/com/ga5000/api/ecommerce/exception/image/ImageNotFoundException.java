package com.ga5000.api.ecommerce.exception.image;

import com.ga5000.api.ecommerce.exception.BaseException;
import org.springframework.http.HttpStatus;

public class ImageNotFoundException extends BaseException {
    public ImageNotFoundException() {
        super("Image not found", HttpStatus.NOT_FOUND);
    }
}
