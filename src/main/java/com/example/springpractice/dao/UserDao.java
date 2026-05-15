package com.example.springpractice.dao;

import com.example.springpractice.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {

  List<User> findAllByOrderByIdAsc();
}
