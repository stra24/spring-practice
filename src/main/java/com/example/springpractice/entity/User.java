package com.example.springpractice.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("users")
public record User(
    @Id
    Long id,
    String name,
    String email,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

  public static User create(String name, String email) {
    LocalDateTime now = LocalDateTime.now();
    return new User(null, name, email, now, now);
  }

  public User update(String name, String email) {
    return new User(this.id, name, email, this.createdAt, LocalDateTime.now());
  }
}
