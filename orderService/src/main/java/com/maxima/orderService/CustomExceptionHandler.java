package com.maxima.orderService;

import com.maxima.orderService.exceptions.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Класс глобального перехватчика ошибок
 */
@ControllerAdvice
public class CustomExceptionHandler {

  /**
   * Обработчик исключения из метода сервиса для генерации корректного http ответа об ошибке
   */
  @ExceptionHandler
  private ResponseEntity<String> handleException(ResponseException e) {
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

}
