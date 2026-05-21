package com.example.springpractice.service;

import com.example.springpractice.dto.UserDto;
import com.example.springpractice.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<UserDto> getUsers() {
    return userRepository.findAll().stream()
        .map(UserDto::from)
        .toList();
  }

  @Transactional(readOnly = true)
  public List<UserDto> findByName(String name) {
    return userRepository.findByName(name).stream()
        .map(UserDto::from)
        .toList();
  }
}