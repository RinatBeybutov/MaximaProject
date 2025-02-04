package com.maxima.orderService;

import com.maxima.orderService.util.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Класс Обработчика исключения для CategoryController
 */
@ControllerAdvice
public class CatExceptionHandler {

    /**
     * Обработчик исключения из метода сервиса для генерации корректного http ответа об ошибке
     */
    @ExceptionHandler
    private ResponseEntity<String> handleException(ResponseException e){
        return new ResponseEntity<>("err", HttpStatus.NOT_FOUND);
    }

}
