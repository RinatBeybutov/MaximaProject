package com.maxima.metricsService.api;

import com.maxima.metricsService.service.MetricsService;
import com.petProject.MetricsService.controller.MetricsApiDelegate;
import com.petProject.MetricsService.dto.OrderMetricsDto;
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
  public ResponseEntity<OrderMetricsDto> getMetrics() {
    return ResponseEntity.ok(service.getMetrics());
  }
}
