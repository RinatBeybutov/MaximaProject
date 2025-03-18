package com.maxima.orderService.controller;

import static com.maxima.orderService.testData.OrderApiTestData.orderCreateDto;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import com.maxima.orderService.config.ApiConfig;
import com.maxima.orderService.config.TestContainersConfig;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.testData.OrderApiTestData;
import java.util.UUID;
import org.junit.Test;
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
  @DisplayName("проверка создания заказа")
  public void testCreate() {
  var response = restTemplate.postForEntity(url, orderCreateDto(), OrderViewDto.class);
  assertEquals(HttpStatus.OK, response.getStatusCode());
  assertNotNull(response.getBody());
  var order = response.getBody();
  assertThat(order)
      .usingRecursiveComparison()
      .ignoringFields("uuid", "createdAt")
      .isEqualTo(orderCreateDto());
  }

  @Test
  @DisplayName("Проверка получения списка заказов по UUID пользователя")
  public void testGetListOfOrders() {
    var response = restTemplate.exchange(url + "/" + OrderApiTestData.ORDER_UUID,
                                         HttpMethod.GET,
                                         null,
                                         OrderViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var orders = response.getBody();
    assertNotNull(orders);
    assertThat(orders).isNotEmpty(); // Проверяем, что заказы не пусты
  }

   @Test
  @DisplayName("Проверка удаления заказа по UUID")
  public void testDeleteOrder() {
    // Сначала создаем заказ для удаления
    var createdOrder = restTemplate.postForEntity(url, OrderApiTestData.getViewOrderDto(), OrderViewDto.class).getBody();
    assertNotNull(createdOrder);
    var uuid = createdOrder.getUuid();

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
  public void testUpdateOrder() {
    // Сначала создаем заказ для обновления
    var createdOrder = restTemplate.postForEntity(url, OrderApiTestData.getViewOrderDto(), OrderViewDto.class).getBody();
    assertNotNull(createdOrder);
    var uuid = createdOrder.getUuid();

    OrderUpdateDto orderUpdateDto = new OrderUpdateDto();

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
    assertEquals(OrderStatus.UPDATED, updatedOrder.getStatus());
  }
 }

