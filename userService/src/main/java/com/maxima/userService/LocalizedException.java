package com.maxima.userService;

import lombok.Getter;

/**
 * Исключение, которое используется для локализованных сообщений об ошибках.
 */
@Getter
public class LocalizedException extends RuntimeException {

  private final Object[] args;

  public LocalizedException(String message, Object... args) {
    super(message);
    this.args = args;
  }
}
