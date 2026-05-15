package com.example.springpractice.dao;

import com.example.springpractice.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

}
