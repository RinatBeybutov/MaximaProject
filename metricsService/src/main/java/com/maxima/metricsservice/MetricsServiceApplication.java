package com.maxima.metricsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Основной класс приложения MetricsService. Этот класс содержит метод main, который является точкой
 * входа в приложение. Он использует Spring Boot для запуска приложения.
 */
@SpringBootApplication
public class MetricsServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(MetricsServiceApplication.class, args);
  }
}
