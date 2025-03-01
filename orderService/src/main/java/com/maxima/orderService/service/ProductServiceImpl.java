package com.maxima.orderService.service;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.exceptions.ResponseException;
import com.maxima.orderService.mapper.ProductMapper;
import com.maxima.orderService.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс Сервиса для реализации работы с Продуктами
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  @Qualifier("productMapper")
  private final ProductMapper mapper;

  @Override
  @Transactional
  public ProductViewDto create(ProductCreateDto dto) throws ResponseException {
    var entity = mapper.toEntity(dto);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductViewDto> getList() {
    return repository.findAll()
        .stream()
        .map(mapper::toDto)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional(readOnly = true)
  public ProductViewDto find(UUID uuid) throws ResponseException {
    var entity = repository.getByUuid(uuid);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  public ProductViewDto update(UUID uuid, ProductCreateDto productCreateDto)
      throws ResponseException {
    var entity = repository.getByUuid(uuid);
    mapper.update(productCreateDto, entity);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  public void delete(UUID uuid) throws ResponseException {
    var entity = repository.getByUuid(uuid);
    repository.delete(entity);
  }
}
