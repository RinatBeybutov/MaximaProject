package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryCreateDto;
import com.maxima.orderService.dto.CategoryDto;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderApiITests extends TestContainersConfig {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Проверка создания объекта")
    public void testCreate() {
        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", TestData.getCategoryCreateDto(), CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto categoryDto = createResponse.getBody();
        String uuid = categoryDto.getUuid().toString();

        assertNotNull(categoryDto);
        assertThat(categoryDto)
                .usingRecursiveComparison()
                .ignoringFields("uuid", "id")
                .isEqualTo(TestData.getCategoryDtoForCreate());

        restTemplate.delete("/api/v1/categories/" + uuid);
    }

    @Test
    @DisplayName("Проверка получения категории по UUID")
    public void testGetOne() {
        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + TestData.CATEGORY_UUID, CategoryDto.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        CategoryDto category = getResponse.getBody();
        assertNotNull(category);
        assertThat(category)
                .usingRecursiveComparison()
                .ignoringFields("uuid", "id")
                .isEqualTo(TestData.getCategoryDto());
    }

    @Test
    @DisplayName("Проверка получения списка категорий")
    public void testGetAll() {
        ResponseEntity<CategoryDto[]> getAllResponse =
                restTemplate.getForEntity("/api/v1/categories", CategoryDto[].class);
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        CategoryDto[] categories = getAllResponse.getBody();
        assertNotNull(categories);
        assertEquals(4, categories.length);
        assertThat(categories[0])
                .usingRecursiveComparison()
                .ignoringFields("uuid", "id")
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
        String uuid = categoryDto.getUuid().toString();

        //обновление
        CategoryCreateDto categoryPutDto = new CategoryCreateDto(TestData.CATEGORY_NAME);
        ResponseEntity<CategoryDto> putResponse =
                restTemplate.exchange("/api/v1/categories/" + uuid, HttpMethod.PUT, new HttpEntity<CategoryCreateDto>(categoryPutDto), CategoryDto.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        //тестирование
        var outputDto = putResponse.getBody();
        assertNotNull(outputDto);
        assertThat(outputDto)
                .usingRecursiveComparison()
                .ignoringFields("uuid", "id")
                .isEqualTo(TestData.getCategoryDtoForUpdate());

        //удаление
        restTemplate.delete("/api/v1/categories/" + uuid);
    }

    @Test
    @DisplayName("Проверка удаления категории")
    public void testDelete() {
        //создание новой категории
        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", TestData.getCategoryCreateDto(), CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto categoryDto = createResponse.getBody();
        String uuid = categoryDto.getUuid().toString();

        //удаление
        restTemplate.delete("/api/v1/categories/" + uuid);
        var categories = restTemplate.getForEntity("/api/v1/categories", CategoryDto[].class)
                .getBody();
        assertNotNull(categories);
        assertEquals(4, categories.length);

        //проверка на Not found
        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + uuid, CategoryDto.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }

    @Test
    @DisplayName("Проверка возврата ошибки при попытке получения отсутствующей категории")
    public void testGetNonExisting() {
        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + TestData.NON_EXISTING_CATEGORY_UUID, CategoryDto.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
    }
}