package com.example.springpractice.controller;

import com.example.springpractice.dto.UserCreateRequest;
import com.example.springpractice.dto.UserDto;
import com.example.springpractice.dto.UserUpdateRequest;
import com.example.springpractice.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping
  public List<UserDto> searchUsers(@RequestParam(defaultValue = "") String name) {
    return userService.findByName(name);
  }

  @GetMapping("/{id}")
  public UserDto findById(@PathVariable @Min(1) Long id) {
    return userService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create(@RequestBody @Valid UserCreateRequest request) {
    return userService.create(request);
  }

  @PutMapping("/{id}")
  public UserDto update(@PathVariable @Min(1) Long id,
      @RequestBody @Valid UserUpdateRequest request) {
    return userService.update(id, request);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable @Min(1) Long id) {
    userService.delete(id);
  }
}
