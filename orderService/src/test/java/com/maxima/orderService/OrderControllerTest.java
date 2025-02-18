package com.maxima.orderService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.maxima.orderService.dto.*;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class OrderControllerTest extends TestContainerConfig{
    @Autowired
    private TestRestTemplate restTemplate;

    private static String createdCategoryUuid;

    @Test
    @DisplayName("Проверка создания объекта")
    public void testCreate() {
        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", TestData.getCategoryCreateDto(), CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto categoryDto = createResponse.getBody();
        createdCategoryUuid = categoryDto.getUuid().toString();

        assertNotNull(categoryDto);
        assertThat(categoryDto)
                .usingRecursiveComparison()
                .ignoringFields("uuid","id")
                .isEqualTo(TestData.getCategoryDtoForCreate());

        restTemplate.delete("/api/v1/categories/" + createdCategoryUuid);
    }

    @Test
    @DisplayName("Проверка получения категории по UUID")
    public void testGetOne() {
        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + TestData.testUUID, CategoryDto.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        CategoryDto categoryFromGet = getResponse.getBody();
        assertNotNull(categoryFromGet);
        assertThat(categoryFromGet)
                .usingRecursiveComparison()
                .ignoringFields("uuid","id")
                .isEqualTo(TestData.getCategoryDto());
    }

    @Test
    @DisplayName("Проверка получения списка категорий")
    public void testGetAll() {
        ResponseEntity<CategoryDto[]> getAllResponse =
                restTemplate.getForEntity("/api/v1/categories", CategoryDto[].class);
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        CategoryDto[] categoryFromGetAll = getAllResponse.getBody();
        assertNotNull(categoryFromGetAll);
        //assertEquals(4, categoryFromGetAll.length);
        assertThat(categoryFromGetAll[0])
                .usingRecursiveComparison()
                .ignoringFields("uuid","id")
                .isEqualTo(TestData.getCategoryDtoFirstInList());
    }

    @Test
    @DisplayName("Проверка обновления категории")
    public void testUpdate() {
        //создание новой категории
        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", TestData.getCategoryCreateDto(), CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto categoryDto = createResponse.getBody();
        createdCategoryUuid = categoryDto.getUuid().toString();

        //обновление
        CategoryCreateDto categoryPutDto = new CategoryCreateDto(TestData.CATEGORY_NAME);
        ResponseEntity<CategoryDto> putResponse =
                restTemplate.exchange("/api/v1/categories/" + createdCategoryUuid, HttpMethod.PUT, new HttpEntity<CategoryCreateDto>(categoryPutDto), CategoryDto.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        //тестирование
        var outputDto=putResponse.getBody();
        assertNotNull(outputDto);
        assertThat(outputDto)
                .usingRecursiveComparison()
                .ignoringFields("uuid","id")
                .isEqualTo(TestData.getCategoryDtoForUpdate());

        //удаление
        restTemplate.delete("/api/v1/categories/" + createdCategoryUuid);
    }

    @Test
    @DisplayName("Проверка удаления категории")
    public void testDelete() {
        restTemplate.delete("/api/v1/categories/" + TestData.testUUID2);
        var categories=restTemplate.getForEntity("/api/v1/categories", CategoryDto[].class)
                .getBody();
        assertNotNull(categories);
        assertEquals(3, categories.length);
    }
}