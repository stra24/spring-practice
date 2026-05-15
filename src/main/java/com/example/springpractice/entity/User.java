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

}
