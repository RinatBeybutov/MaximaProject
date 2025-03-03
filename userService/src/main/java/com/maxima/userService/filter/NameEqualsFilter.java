package com.maxima.userService.filter;

import com.maxima.userService.dto.UserFilterDto;
import com.maxima.userService.entity.UserEntity;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

/**
 * Реализация {@link UserFilter} для фильтрации пользователей по имени
 * Фильтрует пользователей, чье имя соответствует указанному имени (без учета регистра).
 */
@Component
public class NameEqualsFilter implements UserFilter {

  @Override
  public boolean isApplicable(UserFilterDto dto) {
    return dto.getNameEquals() != null && !dto.getNameEquals().isEmpty();
  }

  @Override
  public Stream<UserEntity> filter(Stream<UserEntity> users, UserFilterDto dto) {
    return users.filter(user -> user.getName().equalsIgnoreCase(dto.getNameEquals()));
  }
}
