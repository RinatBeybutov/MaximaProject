package com.maxima.userService.filter;

import com.maxima.userService.dto.UserFilterDto;
import com.maxima.userService.entity.UserEntity;
import java.util.stream.Stream;

/**
 * Интерфейс для фильтров пользователей Каждая реализация этого интерфейса представляет собой
 * отдельный фильтр, который можно применить к потоку пользователей
 */
public interface UserFilter {

  /**
   * Определяет, применим ли данный фильтр к указанным параметрам фильтрации
   *
   * @param dto Объект {@link UserFilterDto} с параметрами фильтрации
   * @return {@code true}, если фильтр применим к указанным параметрам, {@code false} в противном случае
   */
  boolean isApplicable(UserFilterDto dto);

  /**
   * Применяет фильтр к потоку пользователей.
   *
   * @param users Входящий поток пользователей.
   * @param dto   Объект {@link UserFilterDto} с параметрами фильтрации.
   * @return Новый поток пользователей, отфильтрованный в соответствии с параметрами.
   */
  Stream<UserEntity> filter(Stream<UserEntity> users, UserFilterDto dto);
}
