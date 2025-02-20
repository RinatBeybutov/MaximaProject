package com.maxima.userService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дто сущности пользователь
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO для получения пользователя")
public class UserViewDto {

  @Schema(description = "Глобальный идентификатор пользователя", example = "3422b448-2460-4fd2-9183-8000de6f8343")
  private UUID uuid;

  @Schema(description = "Имя пользователя", example = "Иван")
  private String name;

  @Schema(description = "Дата регистрации", example = "2020-01-01")
  private LocalDate registeredAt;

  @Schema(description = "Email пользователя", example = "user@mail.com")
  private String email;
}
