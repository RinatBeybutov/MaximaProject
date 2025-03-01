package com.maxima.orderService.service;

import com.maxima.orderService.OrderMapper;
import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.exceptions.ResponseException;
import com.maxima.orderService.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс Сервиса для реализации работы с Заказами
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository repository;

  private final OrderMapper mapper;

  /**
   * Создать заказ
   */
  @Transactional
  public OrderDto create(OrderCreateDto dto) {
    var entity = mapper.toEntity(dto);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  /**
   * Найти заказ по uuid
   */
  @Transactional
  public OrderDto find(UUID uuid) throws ResponseException {
    var entity = repository.getByUuid(uuid);
    return mapper.toDto(entity);
  }

  /**
   * Обновить заказ по uuid
   */
  @Transactional
  public OrderDto update(UUID uuid, OrderCreateDto orderCreateDto)
      throws ResponseException {
    var entity = repository.getByUuid(uuid);
    mapper.update(orderCreateDto, entity);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  /**
   * Удалить заказ по uuid
   */
  @Transactional
  public void delete(UUID uuid) throws ResponseException {
    var entity = repository.getByUuid(uuid);
    repository.delete(entity);
  }

  /**
   * Получить список всех заказов по uuid пользователя
   */
  @Transactional
  public List<OrderDto> getList(UUID uuid) {
    return repository.findAllByUuid(uuid)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

}
