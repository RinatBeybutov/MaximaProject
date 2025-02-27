package com.maxima.userService;

/**
 * Исключение, которое используется для локализованных сообщений об ошибках.
 */
public class LocalizedException extends RuntimeException {

  public LocalizedException(String messages) {
    super(messages);
  }
}
