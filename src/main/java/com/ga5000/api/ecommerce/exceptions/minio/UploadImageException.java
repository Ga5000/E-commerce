package com.ga5000.api.ecommerce.exceptions.minio;

import com.ga5000.api.ecommerce.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UploadImageException extends BaseException {
    public UploadImageException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
