package com.example.springpractice.row;

public record UserHobbyRow(
    Long userId,
    String userName,
    String userEmail,
    Long hobbyId,
    String hobbyName
) {

}
