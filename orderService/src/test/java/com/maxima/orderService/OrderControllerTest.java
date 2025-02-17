package com.maxima.orderService;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import com.maxima.orderService.dto.*;

import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import org.springframework.http.HttpEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderControllerTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Докер контейнер для postgres
     */
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres=TestContainer.get();

    @Test
    @DisplayName("controller test - create")
    @Order(1)
    public void testCreate() {
        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", TestData.categoryCreateDto, CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto сategoryDto = createResponse.getBody();

        assertNotNull(сategoryDto);
    }
    @Test
    @DisplayName("controller test - get one")
    @Order(2)
    public void testGetOne() {
        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + TestData.UUID, CategoryDto.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        CategoryDto categoryFromGet = getResponse.getBody();

        assert categoryFromGet != null;

        assertEquals("Чипсы", categoryFromGet.getName());
    }


    @Test
    @DisplayName("controller test - get all")
    @Order(3)
    public void testGetAll() {
        ResponseEntity<List<CategoryDto>> getAllResponse =
                restTemplate.exchange("/api/v1/categories", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryDto>>() {
                });
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        List<CategoryDto> categoryFromGetAll = getAllResponse.getBody();
        assert categoryFromGetAll != null;

        assertEquals(5, categoryFromGetAll.size());
    }

    @Test
    @DisplayName("controller test - update")
    @Order(4)
    public void testUpdate() {
        CategoryCreateDto categoryPutDto = new CategoryCreateDto(TestData.CATEGORY_NAME);

        ResponseEntity<CategoryDto> putResponse =
                restTemplate.exchange("/api/v1/categories/" + TestData.UUID, HttpMethod.PUT, new HttpEntity<CategoryCreateDto>(categoryPutDto), CategoryDto.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        CategoryDto categoryFromPut = putResponse.getBody();

        assertNotNull(categoryFromPut);

        assertEquals(TestData.CATEGORY_NAME, categoryFromPut.getName());
    }

    @Test
    @DisplayName("controller test - delete")
    @Order(5)
    public void testDelete() {
        restTemplate.delete("/api/v1/categories/" + TestData.UUID);
        assertEquals(4, restTemplate.exchange("/api/v1/categories", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryDto>>(){} )
                                .getBody()
                                .size());
    }

}