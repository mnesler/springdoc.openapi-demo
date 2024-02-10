package com.mnesler.model;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(User message) {
    super("User already exists: " + message.getUsername());
  }
}
