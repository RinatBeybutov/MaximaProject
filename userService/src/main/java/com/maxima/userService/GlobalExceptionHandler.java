package com.maxima.userService;

import java.util.Locale;
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
public class GlobalExceptionHandler {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(LocalizedException.class)
  public ResponseEntity<String> handleLocalizedException(LocalizedException e,
                                                         WebRequest request) {
    String errorMessage = messageSource.getMessage(e.getMessage(), null, Locale.getDefault());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
  }
}
