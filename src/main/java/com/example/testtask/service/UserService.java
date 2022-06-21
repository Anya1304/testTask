package com.example.testtask.service;

import com.example.testtask.model.Color;
import com.example.testtask.model.User;

import java.util.List;

public interface UserService {
    List<User> findAllByArticlesColor(Color color);

    List<User> findUsersByAgeGreaterThan(Integer age);

    List<String> findAllByCountOfArticles();

    User save(User user);
}
