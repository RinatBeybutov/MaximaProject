package com.maxima.orderService.service;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 * Заглушка для прохождения тестов
 */
@Service
public class ProductServiceImpl implements ProductService {

  @Override
  public ProductViewDto create(ProductCreateDto productCreateDto) {
    return null;
  }

  @Override
  public List<ProductViewDto> getList() {
    return List.of();
  }

  @Override
  public ProductViewDto find(UUID uuid) {
    return null;
  }

  @Override
  public ProductViewDto update(UUID uuid, ProductCreateDto productCreateDto) {
    return null;
  }

  @Override
  public void delete(UUID uuid) {

  }
}
