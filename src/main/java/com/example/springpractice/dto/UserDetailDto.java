package com.example.springpractice.dto;

import java.util.List;

public record UserDetailDto(
    Long id,
    String name,
    String email,
    List<HobbyDto> hobbies
) {

}
