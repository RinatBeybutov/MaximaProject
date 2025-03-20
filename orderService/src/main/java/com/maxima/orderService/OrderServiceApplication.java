package com.maxima.orderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Основной класс приложения OrderService. Этот класс содержит метод main, который является точкой
 * входа в приложение. Он использует Spring Boot для запуска приложения.
 */
@SpringBootApplication
@EnableCaching
public class OrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }

}
