package com.example.springpractice.repository;

import com.example.springpractice.dao.UserDao;
import com.example.springpractice.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final UserDao userDao;

  public List<User> findAll() {
    List<User> list = new ArrayList<>();
    userDao.findAll().forEach(list::add);
    return list;
  }
}
