package com.maxima.userService.controller;

import com.maxima.userService.config.TestContainersConfig;
import com.maxima.userService.config.ApiConfig;
import com.maxima.userService.dto.UserViewDto;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.jdbc.core.JdbcTemplate;

import static com.maxima.userService.testData.UserDtoTestData.getNewUserCreateDto;
import static com.maxima.userService.testData.UserDtoTestData.getNewUserViewDto;
import static com.maxima.userService.testData.UserDtoTestData.getUserCreateDto;
import static com.maxima.userService.testData.UserDtoTestData.getUserViewDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование API для сущности UserEntity")
class UserApiIT extends TestContainersConfig {

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
  @DisplayName("Проверка создания объекта")
  void testCreate() {
    var dto = getUserCreateDto();

    var response = restTemplate.postForEntity(url,
                                              dto,
                                              UserViewDto.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    var user = response.getBody();
    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(dto);
  }

  @Test
  @DisplayName("Проверка получения всех пользователей - пустой список")
  void testGetListEmpty() {
    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         UserViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());
    assertEquals(0, response.getBody().length);
  }

  @Test
  @DisplayName("Проверка получения всех пользователей - список из одной записи")
  void testGetListOfOne() {
    var dto = getUserCreateDto();
    restTemplate.postForEntity(url,
                               dto,
                               UserViewDto.class);

    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         UserViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());

    assertNotNull(response.getBody());

    var users = response.getBody();

    assertEquals(1, users.length);

    assertThat(users[0])
        .usingRecursiveComparison()
        .ignoringFields("uuid")
        .isEqualTo(getUserViewDto());
  }

  @Test
  @DisplayName("Проверка получения всех пользователей - список из нескольких записей")
  void testGetListOfMany() {
    var dto = getUserCreateDto();
    restTemplate.postForEntity(url,
                               dto,
                               UserViewDto.class);

    restTemplate.postForEntity(url,
                               dto,
                               UserViewDto.class);

    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         UserViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    var users = response.getBody();

    assertEquals(2, users.length);
    assertThat(users[0])
        .usingRecursiveComparison()
        .ignoringFields("uuid")
        .isEqualTo(getUserViewDto());
  }

  @Test
  @DisplayName("Проверка на получение пользователя по UUID")
  void testGetOneSuccess() {
    var userBody = restTemplate.postForEntity(url,
                                              getUserCreateDto(),
                                              UserViewDto.class).getBody();

    var uuid = userBody != null ? userBody.getUuid() : null;

    var response = restTemplate.getForEntity(url + "/" + uuid,
                                             UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(userBody, response.getBody());
  }

  @Test
  @DisplayName("Проверка на получение пользователя по UUID - не найден")
  void testGetOneNotFound() {
    var response = restTemplate.getForEntity(url + "/" + UUID.randomUUID(),
                                             Void.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  @DisplayName("Проверка на обновление пользователя по UUID")
  void testUpdateSuccess() {
    var user = restTemplate.postForEntity(url,
                                          getUserCreateDto(),
                                          UserViewDto.class).getBody();

    var uuid = user != null ? user.getUuid() : null;
    var newDto = getNewUserCreateDto();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    var requestEntity = new HttpEntity<>(newDto, headers);

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.PUT,
                                         requestEntity,
                                         UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    user = response.getBody();

    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(getNewUserViewDto());
  }

  @Test
  @DisplayName("Проверка на удаление пользователя по UUID")
  void testDeleteSuccess() {
    var response1 = restTemplate.postForEntity(url,
                                               getUserCreateDto(),
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