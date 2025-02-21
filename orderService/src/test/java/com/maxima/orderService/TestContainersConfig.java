package com.maxima.orderService;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Класс конфигурации контейнеров
 */
@Testcontainers
public class TestContainersConfig {

  /**
   * Докер контейнер для postgres
   */
  @Container
  @ServiceConnection
  public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:latest"
  );
}
