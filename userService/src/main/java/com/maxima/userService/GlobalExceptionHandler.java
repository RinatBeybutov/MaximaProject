package com.maxima.userService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Класс глобального перехватчика ошибок
 */
@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> EntityNotFoundException(EntityNotFoundException e,
                                                        WebRequest request) {
    String[] parts = e.getMessage().split("=");
    String errorKey = parts[0];
    String uuid = parts.length > 1 ? parts[1] : "unknown";
    String errorMessage = messageSource.getMessage(errorKey, new Object[]{uuid}, request.getLocale());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
  }
}
