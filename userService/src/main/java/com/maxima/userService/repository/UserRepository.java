package com.maxima.userService.repository;

import com.maxima.userService.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями пользователей.
 * Предоставляет методы для выполнения операций CRUD с пользователями.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUuid(UUID uuid);

  default UserEntity getUser(UUID uuid) {
    return findByUuid(uuid).orElseThrow(
        () -> new EntityNotFoundException("Пользователь с таким UUID не найден"));
  }
}