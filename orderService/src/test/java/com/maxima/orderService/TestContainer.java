package com.maxima.orderService;

import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainer {

    static PostgreSQLContainer<?> get(){
        return new PostgreSQLContainer<>(
            "postgres"
        );
    }
}
