package com.maxima.metricsservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.maxima.metricsservice.repository.MetricsRedisRepository;
import com.petProject.MetricsService.dto.OrderMetricsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@ContextConfiguration(classes = MetricsServiceApplication.class)
@Testcontainers
public class MetricsRedisRepositoryTest {
  @Container
  private static final GenericContainer<?> redis =
      new GenericContainer<>("redis:7-alpine")
          .withExposedPorts(6379);

  @Autowired
  private MetricsRedisRepository redisRepository;

  @DynamicPropertySource
  static void configureRedis(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", redis::getHost);
    registry.add("spring.redis.port", () -> redis.getMappedPort(6379));
  }

  @Test
  void testTotalOrdersIncrementAndRead() {
    redisRepository.incrementTotalOrders();
    redisRepository.incrementTotalOrders();
    redisRepository.incrementTotalOrders();

    OrderMetricsDto metrics = redisRepository.getOrderMetrics();

    assertEquals(3, metrics.getTotalOrders());
  }
  @BeforeEach
  void setUp() {
    redisRepository.clear();
  }

}
