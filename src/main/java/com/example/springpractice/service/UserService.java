package com.example.springpractice.service;

import com.example.springpractice.dto.HobbyDto;
import com.example.springpractice.dto.UserCreateRequest;
import com.example.springpractice.dto.UserDetailDto;
import com.example.springpractice.dto.UserDto;
import com.example.springpractice.dto.UserUpdateRequest;
import com.example.springpractice.entity.User;
import com.example.springpractice.exception.ResourceNotFoundException;
import com.example.springpractice.repository.UserRepository;
import com.example.springpractice.row.UserHobbyRow;
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
  public UserDetailDto findById(Long id) {
    List<UserHobbyRow> rows = userRepository.findDetailRowsById(id);

    // 1) 取得結果が0件 = ユーザーが存在しない → 例外をスロー
    if (rows.isEmpty()) {
      throw new ResourceNotFoundException("User", String.valueOf(id));
    }

    // 2) 親（ユーザー）の情報は全行共通なので先頭行から取り出す
    UserHobbyRow head = rows.getFirst();

    // 3) 各行の趣味列を HobbyDto に変換してリスト化する（趣味なしの場合は null になるので除外）
    List<HobbyDto> hobbies = rows.stream()
        .filter(r -> r.hobbyId() != null)
        .map(r -> new HobbyDto(r.hobbyId(), r.hobbyName()))
        .toList();

    // 4) UserDetailDto を組み立てて返す
    return new UserDetailDto(head.userId(), head.userName(), head.userEmail(), hobbies);
  }

  @Transactional
  public UserDto create(UserCreateRequest request) {
    User user = User.create(request.name(), request.email());
    User savedUser = userRepository.save(user);
    return UserDto.from(savedUser);
  }

  @Transactional
  public UserDto update(Long id, UserUpdateRequest request) {
    User existingUser = userRepository.findByIdForUpdate(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id)));
    User updatedUser = existingUser.update(request.name(), request.email());
    return UserDto.from(userRepository.save(updatedUser));
  }

  @Transactional
  public UserDto updateSlow(Long id, UserUpdateRequest request) {
    User existingUser = userRepository.findByIdForUpdate(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id)));
    User updatedUser = existingUser.update(request.name(), request.email());
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
    return UserDto.from(userRepository.save(updatedUser));
  }

  @Transactional
  public void delete(Long id) {
    userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User", String.valueOf(id)));
    userRepository.deleteById(id);
  }
}