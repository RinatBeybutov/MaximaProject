package com.maxima.userService.dto.out;

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
public class UserOutDto {
  private UUID uuid;

  private String name;

  private Date registeredAt;

  private String email;
}
