package com.maxima.orderService.controller;

import static com.maxima.orderService.testData.ProductApiTestData.NUMBER_OF_PRODUCTS;
import static com.maxima.orderService.testData.ProductApiTestData.WRONG_UUID;
import static com.maxima.orderService.testData.ProductApiTestData.colaProductDto;
import static com.maxima.orderService.testData.ProductApiTestData.createdViewDto;
import static com.maxima.orderService.testData.ProductApiTestData.productCreateDto;
import static com.maxima.orderService.testData.ProductApiTestData.productUpdateDto;
import static com.maxima.orderService.testData.ProductApiTestData.updatedProductDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.maxima.orderService.config.ApiConfig;
import com.maxima.orderService.config.TestContainersConfig;
import com.maxima.orderService.dto.ProductViewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

/**
 * Класс интеграционных тестов для контроллера ProductController
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование API для сущности ProductEntity")
public class ProductApiIT extends TestContainersConfig {

  @Autowired
  private TestRestTemplate restTemplate;

  private final String url = ApiConfig.PRODUCTS;

  @Test
  @DisplayName("Проверка создания продукта")
  void testCreate() {
    var response = restTemplate.postForEntity(url,
                                              productCreateDto(),
                                              ProductViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var product = response.getBody();
    assertThat(product)
        .usingRecursiveComparison()
        .ignoringFields("uuid")
        .isEqualTo(createdViewDto());

    restTemplate.exchange(url + "/" + product.getUuid(),
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }

  @Test
  @DisplayName("Проверка получения всех продуктов")
  void testGetList() {
    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         ProductViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    var products = response.getBody();

    assertEquals(NUMBER_OF_PRODUCTS, products.length);
    assertThat(products[0])
        .usingRecursiveComparison()
        .isEqualTo(colaProductDto());
  }

  @Test
  @DisplayName("Проверка на получение продукта по UUID")
  void testGetOneSuccess() {
    var product = colaProductDto();

    var response = restTemplate.getForEntity(url + "/" + product.getUuid(),
                                             ProductViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(product, response.getBody());
  }

  @Test
  @DisplayName("Проверка на получение продукта по UUID - не найден")
  void testGetOneNotFound() {
    var response = restTemplate.getForEntity(url + "/" + WRONG_UUID,
                                             String.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @DisplayName("Проверка на обновление продукта по UUID")
  void testUpdate() {
    var product = restTemplate.postForEntity(url,
                                             productCreateDto(),
                                             ProductViewDto.class).getBody();

    assertNotNull(product);

    var uuid = product.getUuid();
    var newDto = productUpdateDto();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.PUT,
                                         new HttpEntity<>(newDto, headers),
                                         ProductViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    product = response.getBody();

    assertThat(product)
        .usingRecursiveComparison()
        .ignoringFields("uuid")
        .isEqualTo(updatedProductDto());

    restTemplate.exchange(url + "/" + uuid,
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }

  @Test
  @DisplayName("Проверка на удаление продукта по UUID")
  void testDeleteSuccess() {
    var productBody = restTemplate.postForEntity(url,
                                                 productCreateDto(),
                                                 ProductViewDto.class).getBody();

    assertNotNull(productBody);

    var uuid = productBody.getUuid();

    var deleteResponse = restTemplate.exchange(url + "/" + uuid,
                                               HttpMethod.DELETE,
                                               null,
                                               Void.class);

    assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
  }
}
