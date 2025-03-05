package com.maxima.orderService.service;

import com.maxima.orderService.mapper.OrderMapper;
import com.maxima.orderService.mapper.ProductToOrderMapper;
import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.dto.ProductToOrderDto;
import com.maxima.orderService.exceptions.ResponseException;
import com.maxima.orderService.repository.OrderRepository;
import com.maxima.orderService.repository.ProductRepository;
import com.maxima.orderService.repository.ProductToOrderRepository;
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

  private final ProductToOrderRepository productToOrderRepository;

  private final ProductRepository productRepository;

  private final OrderMapper mapper;

  private final ProductToOrderMapper productToOrderMapper;

  /**
   * Создать заказ
   */
  @Transactional
  public OrderViewDto create(OrderCreateDto dto) {
    var orderEntity = mapper.toEntity(dto);
    orderEntity = repository.save(orderEntity);
    for (var entry : dto.getProductsNumber().entrySet()) {
      var productToOrderDto = new ProductToOrderDto(productRepository.getByUuid(entry.getKey()).getId(),
                                                    orderEntity.getId(), entry.getValue());
      var productToOrderEntity = productToOrderMapper.toEntity(productToOrderDto);
      productToOrderRepository.save(productToOrderEntity);
    }
    return mapper.toViewDto(orderEntity);
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
  public OrderDto update(UUID uuid, OrderUpdateDto orderUpdateDto)
      throws ResponseException {
    var entity = repository.getByUuid(uuid);
    mapper.update(orderUpdateDto, entity);
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
  public List<OrderDto> toList(UUID userUuid) {
    return repository.findAllByUserUuid(userUuid)
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

}
