package com.example.springpractice.dto;

public record UserUpdateRequest(
    String name,
    String email
) {

}