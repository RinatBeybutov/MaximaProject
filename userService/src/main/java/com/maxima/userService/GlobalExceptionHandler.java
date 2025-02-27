package com.maxima.userService;

import java.util.Locale;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Класс глобального перехватчика ошибок
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler(LocalizedException.class)
  public ResponseEntity<String> handleLocalizedException(LocalizedException e) {
    String errorMessage = messageSource.getMessage(e.getMessage(), e.getArgs(), Locale.getDefault());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
  }
}
