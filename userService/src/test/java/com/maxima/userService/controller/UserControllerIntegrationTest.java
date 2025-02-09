package com.maxima.userService.controller;

import com.maxima.userService.config.TestContainersConfig;
import com.maxima.userService.config.ApiConfig;
import com.maxima.userService.dto.UserDtoTestData;
import com.maxima.userService.dto.UserViewDto;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestContainersConfig.class)
@DisplayName("UserControllerTest")
class UserControllerIntegrationTest {

  @Autowired
  private PostgreSQLContainer<?> postgreSQLContainer;

  @LocalServerPort
  private Integer port;

  @Autowired
  private TestRestTemplate restTemplate;

  private final String url = ApiConfig.USERS;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @BeforeEach
  void setUp() {
    jdbcTemplate.execute("DELETE FROM user_service.users");
  }

  @Test
  @DisplayName("TestCreate")
  void testCreate() {
    var dto = UserDtoTestData.getUserCreateDto();

    var response = restTemplate.postForEntity(url,
                                              dto,
                                              UserViewDto.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals(dto.getName(), response.getBody().getName());
    assertEquals(dto.getEmail(), response.getBody().getEmail());
  }

  @Test
  @DisplayName("TestGetListEmpty")
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
  @DisplayName("TestGetListOfOne")
  void testGetListOfOne() {
    var dto = UserDtoTestData.getUserCreateDto();
    restTemplate.postForEntity(url,
                               dto,
                               UserViewDto.class);

    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         new ParameterizedTypeReference<List<UserViewDto>>() {
                                         });

    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(1, response.getBody().size());

    assertEquals(dto.getName(), response.getBody().get(0).getName());
    assertEquals(dto.getEmail(), response.getBody().get(0).getEmail());
  }

  @Test
  @DisplayName("TestGetListOfMany")
  void testGetListOfMany() {
    var dto = UserDtoTestData.getUserCreateDto();
    restTemplate.postForEntity(url,
                               dto,
                               UserViewDto.class);

    restTemplate.postForEntity(url,
                               dto,
                               UserViewDto.class);

    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         new ParameterizedTypeReference<List<UserViewDto>>() {
                                         });

    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(2, response.getBody().size());

    assertEquals(dto.getName(), response.getBody().get(0).getName());
    assertEquals(dto.getEmail(), response.getBody().get(0).getEmail());

    assertEquals(dto.getName(), response.getBody().get(1).getName());
    assertEquals(dto.getEmail(), response.getBody().get(1).getEmail());
  }

  @Test
  @DisplayName("TestGetOneSuccess")
  void testGetOneSuccess() {
    var userBody = restTemplate.postForEntity(url,
                                              UserDtoTestData.getUserCreateDto(),
                                              UserViewDto.class).getBody();

    var uuid = userBody != null ? userBody.getUuid() : null;

    var response = restTemplate.getForEntity(url + "/" + uuid,
                                             UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userBody, response.getBody());
  }

  @Test
  @DisplayName("TestGetOneNotFound")
  void testGetOneNotFound() {
    var response = restTemplate.getForEntity(url + "/" + UUID.randomUUID(),
                                             Void.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @DisplayName("TestUpdateSuccess")
  void testUpdateSuccess() {
    var user = restTemplate.postForEntity(url,
                                          UserDtoTestData.getUserCreateDto(),
                                          UserViewDto.class).getBody();

    var uuid = user != null ? user.getUuid() : null;
    var newDto = UserDtoTestData.getNewUserCreateDto();
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
  @DisplayName("TestDeleteSuccess")
  void testDeleteSuccess() {
    var response1 = restTemplate.postForEntity(url,
                                               UserDtoTestData.getUserCreateDto(),
                                               UserViewDto.class);

    var uuid = response1.getBody() != null ? response1.getBody().getUuid() : null;

    var response2 = restTemplate.exchange(url + "/" + uuid,
                                          HttpMethod.DELETE,
                                          null,
                                          Void.class);

    assertEquals(HttpStatus.NO_CONTENT, response2.getStatusCode());

    var response3 = restTemplate.getForEntity(url + "/" + uuid,
                                              Void.class);

    assertEquals(HttpStatus.NOT_FOUND, response3.getStatusCode());
  }
}