package com.maxima.orderService.controller;

import static com.maxima.orderService.testData.OrderApiTestData.getViewOrderDto;
import static com.maxima.orderService.testData.OrderApiTestData.orderCreateDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.maxima.orderService.config.ApiConfig;
import com.maxima.orderService.config.TestContainersConfig;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.entity.OrderStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * Класс интеграционных тестов для контроллера OrderController
 */
@DisplayName("Тесты контроллера заказов")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApiIT extends TestContainersConfig {

  @Autowired
  private TestRestTemplate restTemplate;

  private final String url = ApiConfig.ORDERS;

  @Test
  @DisplayName("Проверка создания заказа")
  void testCreate() {
    var response = restTemplate.postForEntity(url, orderCreateDto(), OrderViewDto.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var order = response.getBody();
    assertThat(order)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "createdAt")
        .isEqualTo(orderCreateDto());

    restTemplate.exchange(url + "/" + order.getUuid(),
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }

  @Test
  @DisplayName("Проверка получения списка заказов по UUID пользователя")
  void testGetListOfOrders() {
    var createdOrder = restTemplate.postForEntity(url, orderCreateDto(), OrderViewDto.class)
        .getBody();
    assertNotNull(createdOrder);

    var response = restTemplate.exchange(url + "/" + createdOrder.getUserUuid(),
                                         HttpMethod.GET,
                                         null,
                                         OrderViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var orders = response.getBody();
    assertThat(orders).isNotEmpty();

    restTemplate.exchange(url + "/" + createdOrder.getUuid(),
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }

  @Test
  @DisplayName("Проверка удаления заказа по UUID")
  void testDeleteOrder() {
    // Сначала создаем заказ для удаления
    var createdOrder = restTemplate.postForEntity(url, orderCreateDto(), OrderViewDto.class)
        .getBody();
    assertNotNull(createdOrder);
    var uuid = createdOrder.getUuid();

    // Удаляем заказ
    var deleteResponse = restTemplate.exchange(url + "/" + uuid,
                                               HttpMethod.DELETE,
                                               null,
                                               Void.class);

    assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());

    // Проверяем, что заказ больше не существует
    var response = restTemplate.getForEntity(url + "/" + uuid, OrderViewDto.class);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @DisplayName("Проверка обновления заказа по UUID")
  void testUpdateOrder() {
    // Сначала создаем заказ для обновления
    var createdOrder = restTemplate.postForEntity(url, orderCreateDto(), OrderViewDto.class)
        .getBody();
    assertNotNull(createdOrder);
    var uuid = createdOrder.getUuid();

    // Обновляем заказ
    OrderUpdateDto orderUpdateDto = new OrderUpdateDto();
    orderUpdateDto.setStatus(OrderStatus.IN_PROGRESS);

    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.PUT,
                                         new HttpEntity<>(orderUpdateDto, headers),
                                         OrderViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    var updatedOrder = response.getBody();
    assertNotNull(updatedOrder);
    assertEquals(OrderStatus.IN_PROGRESS, updatedOrder.getStatus());

    restTemplate.exchange(url + "/" + uuid,
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }
}