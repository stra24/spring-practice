package com.example.springpractice.dao;

import com.example.springpractice.entity.User;
import com.example.springpractice.row.UserHobbyRow;
import java.util.List;
import java.util.Optional;
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

  @Query("""
        SELECT *
        FROM users
        WHERE id = :id
        FOR UPDATE
      """)
  Optional<User> findByIdForUpdate(@Param("id") Long id);

  @Query("""
        SELECT
          u.id    AS user_id,
          u.name  AS user_name,
          u.email AS user_email,
          h.id    AS hobby_id,
          h.name  AS hobby_name
        FROM users u
        LEFT JOIN hobbies h ON h.user_id = u.id
        WHERE u.id = :id
        ORDER BY h.id ASC
      """)
  List<UserHobbyRow> findDetailRowsById(@Param("id") Long id);
}
