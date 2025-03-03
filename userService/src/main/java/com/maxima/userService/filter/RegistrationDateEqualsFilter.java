package com.maxima.userService.filter;

import com.maxima.userService.dto.UserFilterDto;
import com.maxima.userService.entity.UserEntity;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link UserFilter} для фильтрации пользователей по дате регистрации
 * Фильтрует пользователей, чья дата регистрации совпадает с указанной
 */
@Component
public class RegistrationDateEqualsFilter implements UserFilter {

  @Override
  public boolean isApplicable(UserFilterDto dto) {
    return dto.getRegistrationDateEquals() != null;
  }

  @Override
  public Stream<UserEntity> filter(Stream<UserEntity> users, UserFilterDto dto) {
    return users.filter(user -> user.getRegisteredAt().isEqual(dto.getRegistrationDateEquals()));
  }
}
