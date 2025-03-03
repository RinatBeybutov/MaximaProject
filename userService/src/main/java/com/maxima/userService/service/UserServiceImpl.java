package com.maxima.userService.service;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserFilterDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.filter.UserFilter;
import com.maxima.userService.mapper.UserMapper;
import com.maxima.userService.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс Сервиса для реализации работы с Пользователями
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  private final UserMapper mapper;

  private final List<UserFilter> filters;

  @Override
  @Transactional
  public UserViewDto create(UserCreateDto dto) {
    var entity = mapper.toEntity(dto);
    entity.setRegisteredAt(LocalDate.now());
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<UserViewDto> getList(UserFilterDto dto) {
    var users = repository.findAll().stream();

    if (dto != null) {
      for (UserFilter userFilter : filters) {
        if (userFilter.isApplicable(dto)) {
          users = userFilter.filter(users, dto);
        }
      }
    }

    return users.map(mapper::toDto)
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