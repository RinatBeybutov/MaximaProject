package com.maxima.orderService.controller;

import com.maxima.orderService.config.ApiConfig;
import com.maxima.orderService.config.TestContainersConfig;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;


/**
 * Класс интеграционных тестов для контроллера OrderController
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DisplayName("Тесты контроллера заказов")
public class OrderApiIT extends TestContainersConfig {

  @Autowired
  private TestRestTemplate restTemplate;

  private final String url = ApiConfig.ORDERS;

  private Object ordersUri;

}
