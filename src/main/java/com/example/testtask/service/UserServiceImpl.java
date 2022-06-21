package com.example.testtask.service;

import com.example.testtask.model.Color;
import com.example.testtask.model.User;
import com.example.testtask.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final static int ARTICLES_COUNT = 3;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllByArticlesColor(Color color) {
        return userRepository.findAllByArticlesColor(color.name());
    }

    @Override
    public List<User> findUsersByAgeGreaterThan(Integer age) {
        if (age == null || age <= 0) {
            throw new IllegalArgumentException("Age can`t be " + age);
        }
        return userRepository.findUsersByAgeGreaterThan(age);
    }

    @Override
    public List<String> findAllByCountOfArticles() {
        return userRepository.findAllByCountOfArticles(ARTICLES_COUNT);
    }

    @Override
    public User save(User user) {
        validateUser(user);
        return userRepository.save(user);
    }

    private void validateUser(User user) {
        if (user.getName() == null) {
            throw new IllegalArgumentException("User name can`t be null");
        }
        if (user.getName().length() < 2) {
            throw new IllegalArgumentException("User name length must be greater then 2");
        }
        if (user.getAge() == null || user.getAge() < 0) {
            throw new IllegalArgumentException("User age must be greater then 0");
        }
    }
}
