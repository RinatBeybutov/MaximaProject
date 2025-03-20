package com.maxima.orderService.config;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Класс конфигурации контейнеров
 */
@Testcontainers
public class TestContainersConfigForProducts {

  /**
   * Докер контейнер для postgres
   */
  @Container
  @ServiceConnection
  public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:latest"
  );
}
