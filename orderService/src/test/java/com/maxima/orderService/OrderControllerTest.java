package com.maxima.orderService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres"
    );

    @Test
    public void testCategoryEndpoints() {
        System.out.println("1. create");         

        // Create a new category
        CategoryCreateDto categoryCreateDto = new CategoryCreateDto("Тестовая категория");

        ResponseEntity<CategoryDto> createResponse =
                restTemplate.postForEntity("/api/v1/categories", categoryCreateDto, CategoryDto.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        CategoryDto сategoryDto = createResponse.getBody();

        assert сategoryDto != null;
 
        //Retrieve
        System.out.println("2. get");  

        ResponseEntity<CategoryDto> getResponse =
                restTemplate.getForEntity("/api/v1/categories/" + "fcc49792-9c0b-49f7-9fce-5d9d631d042f", CategoryDto.class);
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        CategoryDto categoryFromGet = getResponse.getBody();

        assert categoryFromGet != null;

        assertEquals("Чипсы", categoryFromGet.getName());

        // Retrieve All
        System.out.println("3. getAll");  

        ResponseEntity<List<CategoryDto>> getAllResponse =
                restTemplate.exchange("/api/v1/categories", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryDto>>(){} );
        assertEquals(HttpStatus.OK, getAllResponse.getStatusCode());

        List<CategoryDto> categoryFromGetAll = getAllResponse.getBody();
        assert categoryFromGetAll != null;

        assertEquals(5, categoryFromGetAll.size());

        // Put
        System.out.println("4. update");  

        CategoryCreateDto categoryPutDto = new CategoryCreateDto("Тестовая категория1");


        ResponseEntity<CategoryDto> putResponse =
                restTemplate.exchange("/api/v1/categories/" + "fcc49792-9c0b-49f7-9fce-5d9d631d042f", HttpMethod.PUT, new HttpEntity<CategoryCreateDto>(categoryPutDto), CategoryDto.class);
        assertEquals(HttpStatus.OK, putResponse.getStatusCode());

        CategoryDto categoryFromPut = putResponse.getBody();

        assert categoryFromPut != null;

        assertEquals("Тестовая категория1", categoryFromPut.getName());

        // Delete 
        System.out.println("5. delete");

        restTemplate.delete("/api/v1/categories/" + "fcc49792-9c0b-49f7-9fce-5d9d631d042f"); 

        assert 4 == restTemplate.exchange("/api/v1/categories", HttpMethod.GET, null, new ParameterizedTypeReference<List<CategoryDto>>(){} )
                                .getBody()
                                .size();
    }

}