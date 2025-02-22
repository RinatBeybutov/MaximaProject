package com.maxima.orderService.entity;

public enum OrderStatus {
  CREATED(1),
  IN_PROGRESS(2),
  CANCELED(3),
  FINISHED(4);

  private final int status;

  OrderStatus(int status) {
    this.status = status;
  }

  public int getStatus() {
    return status;
  }
}
