package com.example.springpractice.dao;

import com.example.springpractice.entity.User;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserDao extends CrudRepository<User, Long> {

  List<User> findAllByOrderByIdAsc();

  @Query("""
        SELECT * 
        FROM users 
        WHERE name LIKE :namePattern 
        ORDER BY id ASC
      """)
  List<User> findByNameContaining(@Param("namePattern") String namePattern);
}
