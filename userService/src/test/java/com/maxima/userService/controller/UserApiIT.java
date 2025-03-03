package com.maxima.userService.controller;

import static com.maxima.userService.testData.UserApiTestData.NUMBER_OF_USERS;
import static com.maxima.userService.testData.UserApiTestData.USER_NOT_FOUND_MESSAGE;
import static com.maxima.userService.testData.UserApiTestData.WRONG_UUID;
import static com.maxima.userService.testData.UserApiTestData.createdViewDto;
import static com.maxima.userService.testData.UserApiTestData.updatedUserDto;
import static com.maxima.userService.testData.UserApiTestData.userCreateDto;
import static com.maxima.userService.testData.UserApiTestData.userUpdateDto;
import static com.maxima.userService.testData.UserApiTestData.vladimirUserDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.maxima.userService.config.ApiConfig;
import com.maxima.userService.config.TestContainersConfig;
import com.maxima.userService.dto.UserViewDto;
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


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование API для сущности UserEntity")
class UserApiIT extends TestContainersConfig {

  @Autowired
  private TestRestTemplate restTemplate;

  private final String url = ApiConfig.USERS;

  @Test
  @DisplayName("Проверка создания объекта")
  void testCreate() {
    var response = restTemplate.postForEntity(url,
                                              userCreateDto(),
                                              UserViewDto.class);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertNotNull(response.getBody());
    var user = response.getBody();
    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(createdViewDto());

    restTemplate.exchange(url + "/" + user.getUuid(),
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }

  @Test
  @DisplayName("Проверка получения всех пользователей")
  void testGetListOfMany() {
    var response = restTemplate.exchange(url,
                                         HttpMethod.GET,
                                         null,
                                         UserViewDto[].class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    var users = response.getBody();

    assertEquals(NUMBER_OF_USERS, users.length);
    assertThat(users[0])
        .usingRecursiveComparison()
        .isEqualTo(vladimirUserDto());
  }

  @Test
  @DisplayName("Проверка на получение пользователя по UUID")
  void testGetOneSuccess() {
    var user = vladimirUserDto();

    var response = restTemplate.getForEntity(url + "/" + user.getUuid(),
                                             UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(user, response.getBody());
  }

  @Test
  @DisplayName("Проверка на получение пользователя по UUID - не найден")
  void testGetOneNotFound() {
    var response = restTemplate.getForEntity(url + "/" + WRONG_UUID,
                                             String.class);

    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals(USER_NOT_FOUND_MESSAGE.formatted(WRONG_UUID), response.getBody());
  }

  @Test
  @DisplayName("Проверка на обновление пользователя по UUID")
  void testUpdateSuccess() {
    var user = restTemplate.postForEntity(url,
                                          userCreateDto(),
                                          UserViewDto.class).getBody();

    assertNotNull(user);

    var uuid = user.getUuid();
    var newDto = userUpdateDto();
    var headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    var response = restTemplate.exchange(url + "/" + uuid,
                                         HttpMethod.PUT,
                                         new HttpEntity<>(newDto, headers),
                                         UserViewDto.class);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());

    user = response.getBody();

    assertThat(user)
        .usingRecursiveComparison()
        .ignoringFields("uuid", "registeredAt")
        .isEqualTo(updatedUserDto());

    restTemplate.exchange(url + "/" + uuid,
                          HttpMethod.DELETE,
                          null,
                          Void.class);
  }

  @Test
  @DisplayName("Проверка на удаление пользователя по UUID")
  void testDeleteSuccess() {
    var userBody = restTemplate.postForEntity(url,
                                              userCreateDto(),
                                              UserViewDto.class).getBody();

    assertNotNull(userBody);

    var uuid = userBody.getUuid();

    var deleteResponse = restTemplate.exchange(url + "/" + uuid,
                                               HttpMethod.DELETE,
                                               null,
                                               Void.class);

    assertEquals(HttpStatus.NO_CONTENT, deleteResponse.getStatusCode());

    var errorResponse = restTemplate.getForEntity(url + "/" + uuid,
                                                  String.class);

    assertEquals(HttpStatus.NOT_FOUND, errorResponse.getStatusCode());
    assertEquals(USER_NOT_FOUND_MESSAGE.formatted(uuid), errorResponse.getBody());
  }
}