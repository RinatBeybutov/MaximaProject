package com.maxima.userService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxima.userService.config.ApiConfig;
import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.mapper.UserMapper;
import com.maxima.userService.repository.UserRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserControllerIntegrationTest {

  @Container
  @ServiceConnection
  private static final PostgreSQLContainer<?> postgreSQLContainer =
      new PostgreSQLContainer<>("postgres:latest");

  @LocalServerPort
  private Integer port;

  @Autowired
  private UserMapper mapper;

  @Autowired
  private UserRepository repository;

  @Autowired
  private TestRestTemplate restTemplate;

  private static UserCreateDto dto;

  private String url;

  @BeforeAll
  static void setDto() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      dto = objectMapper.readValue(
          new File("src/test/resources/user_create_dto_example.json"), UserCreateDto.class);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @BeforeEach
  void setUp() {
    repository.deleteAll();
    url = "http://localhost:" + port + ApiConfig.USERS;
  }

  @Test
  void testCreate() {
    var response = restTemplate.postForEntity(url, dto,
                                              UserViewDto.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
  }

  @Test
  void testGetListEmpty() {
    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         new ParameterizedTypeReference<List<UserViewDto>>() {
                                         });

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().size());
  }

  @Test
  void testGetListOfOne() {
    repository.save(mapper.toEntity(dto));

    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         new ParameterizedTypeReference<List<UserViewDto>>() {
                                         });

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().size());
  }

  @Test
  void testGetListOfMany() {
    repository.save(mapper.toEntity(dto));
    repository.save(mapper.toEntity(dto));

    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         new ParameterizedTypeReference<List<UserViewDto>>() {
                                         });

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());
  }

  @Test
  void testGetOneSuccess() {
    var user = repository.save(mapper.toEntity(dto));
    var uuid = user.getUuid();

    var response = restTemplate.getForEntity(url + "/" + uuid,
                                             UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(mapper.toDto(user), response.getBody());
  }

  @Test
  void testGetOneNotFound() {
    var user = repository.save(mapper.toEntity(dto));
    var uuid = user.getUuid();
    repository.deleteAll();

    var response = restTemplate.getForEntity(url + "/" + uuid, Void.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void testUpdateSuccess() {
    var user = repository.save(mapper.toEntity(dto));
    var uuid = user.getUuid();
    var newDto = new UserCreateDto("new name", "newemail@mail.ru");
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var requestEntity = new HttpEntity<>(newDto, headers);

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.PUT,
                                         requestEntity,
                                         UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(newDto.getName(), response.getBody().getName());
    assertEquals(newDto.getEmail(), response.getBody().getEmail());
  }

  @Test
  void testUpdateNotFound() {
    var user = repository.save(mapper.toEntity(dto));
    var uuid = user.getUuid();
    repository.deleteAll();

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.PUT,
                                         null,
                                         Void.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  void testDeleteSuccess() {
    var user = repository.save(mapper.toEntity(dto));
    var uuid = user.getUuid();

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.DELETE,
                                         null,
                                         Void.class);

    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    assertEquals(0, repository.findAll().size());
  }

  @Test
  void testDeleteNotFound() {
    var user = repository.save(mapper.toEntity(dto));
    var uuid = user.getUuid();
    repository.deleteAll();

    var response = restTemplate.exchange(url + "/" + uuid,
                                     HttpMethod.DELETE,
                                     null,
                                     Void.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }
}