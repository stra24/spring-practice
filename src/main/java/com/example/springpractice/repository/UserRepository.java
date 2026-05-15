package com.example.springpractice.repository;

import com.example.springpractice.dao.UserDao;
import com.example.springpractice.entity.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final UserDao userDao;

  public List<User> findAll() {
    return userDao.findAllByOrderByIdAsc();
  }
}
