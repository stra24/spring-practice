package com.example.springpractice.dto;

import com.example.springpractice.entity.User;

public record UserDto(
    Long id,
    String name,
    String email
) {

  public static UserDto from(User user) {
    return new UserDto(
        user.id(),
        user.name(),
        user.email()
    );
  }
}