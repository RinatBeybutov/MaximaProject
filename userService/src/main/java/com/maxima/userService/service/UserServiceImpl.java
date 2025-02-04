package com.maxima.userService.service;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.mapper.UserMapper;
import com.maxima.userService.repository.UserRepository;
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
  public UserViewDto create(UserCreateDto dto) {
    var entity = mapper.toEntity(dto);
    entity = repository.save(entity);
    return mapper.toDto(entity);
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
    var entity = repository.getUser(uuid);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  public UserViewDto update(UserCreateDto dto, UUID uuid) {
    var entity = repository.getUser(uuid);
    mapper.update(dto, entity);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  public void delete(UUID uuid) {
    repository.delete(repository.getUser(uuid));
  }
}