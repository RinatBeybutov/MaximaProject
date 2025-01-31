package com.maxima.userService.controller;

import com.maxima.userService.dto.inside.UserInsideDto;
import com.maxima.userService.dto.out.UserOutDto;
import com.maxima.userService.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserOutDto> addUser(@Valid @RequestBody UserInsideDto userInsideDto) {
    return new ResponseEntity<>(userService.addUser(userInsideDto), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<UserOutDto>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping("/{uuid}")
  public ResponseEntity<UserOutDto> getUserByUUID(@PathVariable UUID uuid) {
    UserOutDto user = userService.getUserByUUID(uuid);
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PutMapping("/{uuid}")
  public ResponseEntity<UserOutDto> updateUserByUUID(
      @Valid @RequestBody UserInsideDto userInsideDto,
      @PathVariable UUID uuid) {
    return new ResponseEntity<>(userService.updateUserByUUID(userInsideDto, uuid), HttpStatus.OK);
  }

  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> deleteUserByUUID(@PathVariable UUID uuid) {
    userService.deleteUserByUUID(uuid);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
