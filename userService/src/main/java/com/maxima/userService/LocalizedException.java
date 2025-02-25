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
public class LocalizedException extends RuntimeException {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e,
                                                              WebRequest request) {
    String errorMessage = messageSource.getMessage(e.getMessage(), null, request.getLocale());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
  }
}
