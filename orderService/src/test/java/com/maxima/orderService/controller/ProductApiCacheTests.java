package com.maxima.orderService.controller;

import static com.maxima.orderService.config.ApiConfig.PRODUCTS;
import static com.maxima.orderService.testData.ProductApiTestData.mockProductEntity;
import static com.maxima.orderService.testData.ProductApiTestData.productCreateDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.maxima.orderService.config.ApiConfig;
import com.maxima.orderService.config.TestContainersConfig;
import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.repository.ProductRepository;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;

/**
 * Класс теста кэша для контроллера ProductController
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование кэша ProductController")
@Import(TestContainersConfig.class)
class ProductApiCacheTests {

  @MockBean
  private ProductRepository repository;

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Проверка работы кэша")
  void testCache() {
    Mockito.when(repository.getByUuid(Mockito.any())).thenReturn(mockProductEntity());
    Mockito.when(repository.save(Mockito.any())).thenReturn(mockProductEntity());
    // Создание продукта
    var response = restTemplate.postForEntity(PRODUCTS,
                                              productCreateDto(),
                                              ProductViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    var product = response.getBody();

    var response2 = restTemplate.getForEntity(PRODUCTS + "/" + product.getUuid(),
                                              ProductViewDto.class);
    var response3 = restTemplate.getForEntity(PRODUCTS + "/" + product.getUuid(),
                                              ProductViewDto.class);

    Cache productsCache = cacheManager.getCache("products");
    var cachedValue = productsCache.get(
        UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d045f"), ProductViewDto.class);

    assertNotNull(cachedValue);
    assertThat(cachedValue.getUuid())
        .isEqualTo(product.getUuid());

    Mockito.verify(repository, Mockito.times(1)).getByUuid(product.getUuid());
  }
}
