package com.mnesler.repository;

import com.mnesler.model.User;
import com.mnesler.model.UserAlreadyExistsException;
import com.mnesler.model.UserEntity;
import com.mnesler.model.UserNotFoundException;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepository {
  private static final Logger log = LoggerFactory.getLogger(UserRepository.class);

  private final Map<String, UserEntity> users = new ConcurrentHashMap<>();

  public UserEntity save(User user) {
    log.info("Persisting user {}", user);
    validate(user);
    final UserEntity entity = UserEntity.from(user);
    users.put(entity.getUsername(), entity);
    return entity;
  }

  public UserEntity get(@NonNull String username) {
    return Optional.of(username).map(users::get).orElseThrow(() -> handleUserNotFound(username));
  }

  private void validate(User user) {
    if (users.containsKey(user.getUsername())) {
      throw new UserAlreadyExistsException(user);
    }
  }

  private static RuntimeException handleUserNotFound(String userId) {
    RuntimeException userNotFound = new UserNotFoundException("User not found");
    log.error("User not found {}", userId, userNotFound);
    return userNotFound;
  }
}
