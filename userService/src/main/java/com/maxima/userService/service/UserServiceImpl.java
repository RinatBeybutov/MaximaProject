package com.maxima.userService.service;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.entity.UserEntity;
import com.maxima.userService.mapper.UserMapper;
import com.maxima.userService.repository.UserEntityRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserEntityRepository repository;

  private final UserMapper mapper;

  @Override
  public ResponseEntity<UserViewDto> create(UserCreateDto userCreateDto) {
    return ResponseEntity.ok(
        mapper.toUserViewDto(repository.save(mapper.toUserEntity(userCreateDto))));
  }

  @Override
  public ResponseEntity<List<UserViewDto>> getList() {
    return ResponseEntity.ok(repository.findAll().stream()
                                 .map(mapper::toUserViewDto)
                                 .toList());
  }

  @Override
  public ResponseEntity<UserViewDto> getOne(UUID uuid) {
    return repository.findByUuid(uuid)
        .map(userEntity -> ResponseEntity.ok(mapper.toUserViewDto(userEntity)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Override
  public ResponseEntity<UserViewDto> update(UserCreateDto userCreateDto, UUID uuid) {
    Optional<UserEntity> entity = repository.findByUuid(uuid);
    if (entity.isPresent()) {
      entity.get().setEmail(userCreateDto.getEmail());
      entity.get().setName(userCreateDto.getName());
      return ResponseEntity.ok(mapper.toUserViewDto(repository.save(entity.get())));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @Override
  @Transactional
  public ResponseEntity<Void> delete(UUID uuid) {
    if (repository.findByUuid(uuid).isPresent()) {
      repository.removeByUuid(uuid);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}