package com.maxima.userService.repository;

import com.maxima.userService.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByUuid(UUID uuid);

  void removeByUuid(UUID uuid);

}