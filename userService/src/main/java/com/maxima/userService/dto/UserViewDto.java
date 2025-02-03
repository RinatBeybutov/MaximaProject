package com.maxima.userService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO для получения пользователя")
public class UserViewDto {

  @Schema(description = "UUID пользователя", example = "1")
  private UUID uuid;

  @Schema(description = "Имя пользователя", example = "Иван")
  private String name;

  @Schema(description = "Дата регистрации", example = "2020-01-01")
  private Date registeredAt;

  @Schema(description = "Email пользователя", example = "user@mail.com")
  private String email;
}
