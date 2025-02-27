package com.maxima.userService.repository;

import com.maxima.userService.LocalizedException;
import com.maxima.userService.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями пользователей.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUuid(UUID uuid);

  default UserEntity getUser(UUID uuid) {
    return findByUuid(uuid).orElseThrow(
        () -> new LocalizedException("error.user.not_found"));
  }
}