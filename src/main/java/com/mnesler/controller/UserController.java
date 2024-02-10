package com.mnesler.controller;

import com.mnesler.model.User;
import com.mnesler.model.UserAlreadyExistsException;
import com.mnesler.model.UserEntity;
import com.mnesler.model.UserNotFoundException;
import com.mnesler.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  private final UserRepository repository;

  @Operation(summary = "Create a new user")
  @PostMapping
  @ApiResponse(
      responseCode = "200",
      description = "Create User",
      content = @Content(schema = @Schema(implementation = UserEntity.class)))
  @ApiResponse(
      responseCode = "400",
      description = "User already exists",
      content = @Content(schema = @Schema(implementation = UserAlreadyExistsException.class)))
  public UserEntity createUser(@RequestBody @NonNull User user) {
    log.info("Creating user {}", user.getUsername());
    return repository.save(user);
  }

  @Operation(summary = "Fetch an existing user")
  @GetMapping("/{username}")
  @ApiResponse(
      responseCode = "200",
      description = "Fetch User",
      content = @Content(schema = @Schema(implementation = UserEntity.class)))
  @ApiResponse(
      responseCode = "404",
      description = "User not found",
      content = @Content(schema = @Schema(implementation = UserNotFoundException.class)))
  public UserEntity fetchUser(@PathVariable("username") @NonNull String username) {
    log.info("Fetching  user{}", username);
    return repository.get(username);
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
