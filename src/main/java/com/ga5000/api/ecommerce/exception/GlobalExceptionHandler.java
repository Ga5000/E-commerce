package com.ga5000.api.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private ResponseEntity<Map<String, String>> buildErrorResponse(HttpStatus status, String message) {
    return ResponseEntity.status(status)
            .body(Collections.singletonMap("error", message));
  }

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<Map<String, String>> handleCustomException(BaseException ex) {
    return buildErrorResponse(ex.getStatus(), ex.getMessage());
  }

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<Map<String, String>> handleNullPointerException(NullPointerException ex) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "A required value was null");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, String>> handleGenericException(Exception ex) {
    return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }
}
