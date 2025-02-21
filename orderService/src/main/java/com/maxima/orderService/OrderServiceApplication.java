package com.maxima.orderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения OrderService. Этот класс содержит метод main, который является точкой
 * входа в приложение. Он использует Spring Boot для запуска приложения.
 */
@SpringBootApplication
public class OrderServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderServiceApplication.class, args);
  }

}
