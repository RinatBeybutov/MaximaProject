package com.maxima.metricsservice.api;

import com.maxima.metricsservice.service.MetricsService;
import com.petProject.MetricsService.controller.MetricsApiDelegate;
import com.petProject.MetricsService.dto.OrderMetricsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MetricsApiDelegateImpl implements MetricsApiDelegate {

  private final MetricsService service;


  @Override
  public ResponseEntity<OrderMetricsDto> getMetrics() {
    return ResponseEntity.ok(service.getMetrics());
  }
}
