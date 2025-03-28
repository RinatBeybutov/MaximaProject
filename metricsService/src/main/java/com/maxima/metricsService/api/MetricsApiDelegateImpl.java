package com.maxima.metricsService.api;

import com.maxima.metricsService.dto.OrderMetricsDto;
import com.maxima.metricsService.service.MetricsService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Реализация Апи для метрик заказов.
 */
@Component
@RequiredArgsConstructor
public class MetricsApiDelegateImpl implements MetricsApiDelegate {

  private final MetricsService service;

  @Override
  public ResponseEntity<OrderMetricsDto> metricsOrdersGet(LocalDate date) {
    return ResponseEntity.ok(service.getMetrics());
  }
}
