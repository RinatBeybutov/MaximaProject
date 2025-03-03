package com.maxima.orderService.service;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.mapper.ProductMapper;
import com.maxima.orderService.repository.CategoryRepository;
import com.maxima.orderService.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс Сервиса для реализации работы с Продуктами
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  private final CategoryRepository categoryRepository;

  private final ProductMapper productMapper;

  @Override
  @Transactional
  public ProductViewDto create(ProductCreateDto dto) {
    var category = categoryRepository.getByUuid(dto.getCategoryUuid());
    var entity = productMapper.toEntity(dto);
    entity.setCategory(category);
    entity = repository.save(entity);
    return productMapper.toDto(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public List<ProductViewDto> getList() {
    return repository.findAll()
        .stream()
        .map(productMapper::toDto)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public ProductViewDto find(UUID uuid) {
    var entity = repository.getByUuid(uuid);
    return productMapper.toDto(entity);
  }

  @Override
  @Transactional
  public ProductViewDto update(UUID uuid, ProductCreateDto productCreateDto) {
    var entity = repository.getByUuid(uuid);
    var category = categoryRepository.getByUuid(productCreateDto.getCategoryUuid());
    productMapper.update(productCreateDto, entity);
    entity.setCategory(category);
    entity = repository.save(entity);
    return productMapper.toDto(entity);
  }

  @Override
  @Transactional
  public void delete(UUID uuid) {
    var entity = repository.getByUuid(uuid);
    repository.delete(entity);
  }
}
