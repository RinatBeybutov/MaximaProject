package com.maxima.orderService.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * Класс конфигурации контейнеров
 */
//@Testcontainers
@TestConfiguration
public class TestContainersConfig {

  /**
   * Докер контейнер для postgres
   */
  //@Container
  //@ServiceConnection
  private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
      "postgres:latest"
  )
      .withDatabaseName("testdb")
      .withUsername("test")
      .withPassword("test");

  static {
    postgres.start();

    System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
    System.setProperty("spring.datasource.password", postgres.getPassword());
    System.setProperty("spring.datasource.username", postgres.getUsername());

    System.setProperty("spring.flyway.url", postgres.getJdbcUrl());
    System.setProperty("spring.flyway.user", postgres.getUsername());
    System.setProperty("spring.flyway.password", postgres.getPassword());
  }

}
