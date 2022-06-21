package com.example.testtask.repository;

import com.example.testtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUsersByAgeGreaterThan(Integer age);

    @Query(value = "SELECT * FROM users u INNER JOIN articles a ON (a.user_id = u.id) WHERE a.color = :color",
            nativeQuery = true)
    List<User> findAllByArticlesColor(@Param("color") String color);

    @Query(value = """
            SELECT users.name FROM
            (SELECT u.name, COUNT(a.id) AS count1 FROM users u
            INNER JOIN articles a ON (a.user_id = u.id) GROUP BY u.name) users WHERE users.count1 >= :count""",
            nativeQuery = true)
    List<String> findAllByCountOfArticles(@Param("count") int articlesCount);
}
