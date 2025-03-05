package com.maxima.userService.filter;

import com.maxima.userService.dto.UserFilterDto;
import com.maxima.userService.entity.UserEntity;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link UserFilter} для фильтрации пользователей по дате регистрации
 * Фильтрует пользователей, зарегистрированных до указанной даты
 */
@Component
public class RegistrationDateBeforeFilter implements UserFilter {

  @Override
  public boolean isApplicable(UserFilterDto dto) {
    return dto.getRegistrationDateBefore() != null;
  }

  @Override
  public Stream<UserEntity> filter(Stream<UserEntity> users, UserFilterDto dto) {
    return users.filter(
        user -> user.getRegisteredAt().isBefore(dto.getRegistrationDateBefore()));
  }
}
