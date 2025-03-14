package com.maxima.orderService.service;

import com.maxima.orderService.dto.ProductWithCountDto;
import com.maxima.orderService.entity.OrderEntity;
import com.maxima.orderService.entity.OrderStatus;
import com.maxima.orderService.entity.ProductToOrderEntity;
import com.maxima.orderService.mapper.OrderMapper;
import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.mapper.ProductMapper;
import com.maxima.orderService.repository.OrderRepository;
import com.maxima.orderService.repository.ProductRepository;
import com.maxima.orderService.repository.ProductToOrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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

  private final ProductMapper productMapper;

  /**
   * Создать заказ
   */
  @Transactional
  @Override
  public OrderViewDto create(OrderCreateDto dto) {
    var orderEntity = mapper.toEntity(dto);
    orderEntity.setCreatedAt(LocalDateTime.now());
    orderEntity.setStatus(OrderStatus.CREATED);
    orderEntity = repository.save(orderEntity);
    saveProducts(dto, orderEntity);
    return mapToViewDto(orderEntity);
  }

  /**
   * Найти заказ по uuid
   */
  @Transactional(readOnly = true)
  @Override
  public OrderViewDto getOne(UUID uuid) {
    var entity = repository.getByUuid(uuid);
    return mapToViewDto(entity);
  }

  /**
   * Обновить заказ по uuid
   */
  @Transactional
  @Override
  public OrderViewDto update(UUID uuid, OrderUpdateDto orderUpdateDto) {
    var entity = repository.getByUuid(uuid);
    mapper.update(orderUpdateDto, entity);
    entity = repository.save(entity);
    return mapToViewDto(entity);
  }

  /**
   * Удалить заказ по uuid
   */
  @Transactional
  @Override
  public void delete(UUID uuid) {
    var entity = repository.getByUuid(uuid);
    repository.delete(entity);
  }

  /**
   * Получить список всех заказов по uuid пользователя
   */
  @Transactional(readOnly = true)
  @Override
  public List<OrderViewDto> getList(UUID userUuid) {
    return repository.findAllByUserUuid(userUuid)
        .stream()
        .map(this::mapToViewDto)
        .toList();
  }

  private List<ProductWithCountDto> getProducts(Long orderId) {
    return productToOrderRepository.findAllByOrderId(orderId)
        .stream()
        .map(productMapper::toCountDto)
        .toList();
  }

  private OrderViewDto mapToViewDto(OrderEntity orderEntity) {
    OrderViewDto dto = mapper.toViewDto(orderEntity);
    var products = getProducts(orderEntity.getId());
    dto.setProducts(products);
    return dto;
  }

  private void saveProducts(OrderCreateDto dto, OrderEntity orderEntity) {
    for (var product : dto.getProducts()) {
      var productToOrderEntity = new ProductToOrderEntity();
      productToOrderEntity.setOrder(orderEntity);
      var productEntity = productRepository.getByUuid(product.getUuid());
      productToOrderEntity.setProduct(productEntity);
      productToOrderEntity.setCount(product.getCount());
      productToOrderRepository.save(productToOrderEntity);
    }
  }
}
