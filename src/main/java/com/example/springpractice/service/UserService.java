package com.example.springpractice.service;

import com.example.springpractice.dto.UserCreateRequest;
import com.example.springpractice.dto.UserDto;
import com.example.springpractice.dto.UserUpdateRequest;
import com.example.springpractice.entity.User;
import com.example.springpractice.exception.ResourceNotFoundException;
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

  @Transactional(readOnly = true)
  public UserDto findById(Long id) {
    return userRepository.findById(id)
        .map(UserDto::from)
        .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id)));
  }

  @Transactional
  public UserDto create(UserCreateRequest request) {
    User user = User.create(request.name(), request.email());
    User savedUser = userRepository.save(user);
    return UserDto.from(savedUser);
  }

  @Transactional
  public UserDto update(Long id, UserUpdateRequest request) {
    User existingUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id)));
    User updatedUser = existingUser.update(request.name(), request.email());
    return UserDto.from(userRepository.save(updatedUser));
  }

  @Transactional
  public void delete(Long id) {
    userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id)));
    userRepository.deleteById(id);
  }

  @Transactional
  public void createAndThrow(UserCreateRequest request) {
    User user = User.create(request.name(), request.email());
    userRepository.save(user);
    throw new RuntimeException("ロールバック確認用の例外");
  }
}