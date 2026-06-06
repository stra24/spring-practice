package com.example.springpractice.repository;

import com.example.springpractice.dao.UserDao;
import com.example.springpractice.entity.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final UserDao userDao;

  public List<User> findAll() {
    return userDao.findAllByOrderByIdAsc();
  }

  public List<User> findByName(String name) {
    String namePattern = "%" + name + "%";
    return userDao.findByNameContaining(namePattern);
  }

  public Optional<User> findById(Long id) {
    return userDao.findById(id);
  }

  public User save(User user) {
    return userDao.save(user);
  }
}
