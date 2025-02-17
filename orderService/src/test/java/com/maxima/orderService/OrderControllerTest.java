package com.maxima.orderService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.maxima.orderService.dto.*;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

import  org.springframework.boot.testcontainers.context.ImportTestcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ImportTestcontainers(value = {TestContainerConfig.class})
public class OrderControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    private static String createdCategoryUuid;

    @Test
    @DisplayName("тест контроллера - создать")
    @Order(1)
    public void testCreate() {
        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", TestData.getCategoryCreateDto(), CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto categoryDto = createResponse.getBody();
        createdCategoryUuid = categoryDto.getUuid().toString();

        assertNotNull(categoryDto);
    }

    @Test
    @DisplayName("тест контроллера - получить 1 значение")
    @Order(2)
    public void testGetOne() {
        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + TestData.testUUID, CategoryDto.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        CategoryDto categoryFromGet = getResponse.getBody();
        assertNotNull(categoryFromGet);
        assertEquals(TestData.getCategoryDto(), categoryFromGet);
    }

    @Test
    @DisplayName("тест контроллера - получить все значения")
    @Order(3)
    public void testGetAll() {
        ResponseEntity<CategoryDto[]> getAllResponse =
                restTemplate.getForEntity("/api/v1/categories", CategoryDto[].class);
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        CategoryDto[] categoryFromGetAll = getAllResponse.getBody();
        assertNotNull(categoryFromGetAll);
        assertEquals(5, categoryFromGetAll.length);
    }

    @Test
    @DisplayName("тест контроллера - обновить")
    @Order(4)
    public void testUpdate() {
        CategoryCreateDto categoryPutDto = new CategoryCreateDto(TestData.CATEGORY_NAME);

        ResponseEntity<CategoryDto> putResponse =
                restTemplate.exchange("/api/v1/categories/" + TestData.testUUID, HttpMethod.PUT, new HttpEntity<CategoryCreateDto>(categoryPutDto), CategoryDto.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        CategoryDto categoryFromPut = putResponse.getBody();
        assertNotNull(categoryFromPut);
        assertEquals(TestData.CATEGORY_NAME, categoryFromPut.getName());
    }

    @Test
    @DisplayName("тест контроллера - удалить")
    @Order(5)
    public void testDelete() {
        restTemplate.delete("/api/v1/categories/" + createdCategoryUuid);
        assertEquals(4, restTemplate.exchange("/api/v1/categories", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryDto>>(){} )
                                .getBody()
                                .size());
    }
}