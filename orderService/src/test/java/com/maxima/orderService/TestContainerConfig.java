package com.maxima.orderService;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public class TestContainerConfig {
    /**
     * Докер контейнер для postgres
     */
    @Container
    @ServiceConnection
    public static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:latest"
    );
}
