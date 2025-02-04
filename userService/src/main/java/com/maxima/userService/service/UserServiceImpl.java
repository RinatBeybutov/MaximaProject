package com.maxima.userService.service;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.entity.UserEntity;
import com.maxima.userService.mapper.UserMapper;
import com.maxima.userService.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  private final UserMapper mapper;

  @Override
  @Transactional
  public UserViewDto create(UserCreateDto userCreateDto) {
    return mapper.toDto(repository.save(mapper.toEntity(userCreateDto)));
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserViewDto> getList() {
    return repository.findAll().stream()
        .map(mapper::toDto)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public UserViewDto getOne(UUID uuid) {
    return mapper.toDto(find(uuid));
  }

  @Override
  @Transactional
  public UserViewDto update(UserCreateDto userCreateDto, UUID uuid) {
    UserEntity entity = find(uuid);
    entity.setEmail(userCreateDto.getEmail());
    entity.setName(userCreateDto.getName());
    return mapper.toDto(repository.save(entity));
  }

  @Override
  @Transactional
  public void delete(UUID uuid) {
    repository.delete(find(uuid));
  }

  private UserEntity find(UUID uuid) {
    return repository.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
  }
}