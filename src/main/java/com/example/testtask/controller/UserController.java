package com.example.testtask.controller;

import com.example.testtask.model.Color;
import com.example.testtask.model.User;
import com.example.testtask.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/save")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping("/users/{age}")
    public List<User> getUsersByAge(@PathVariable Integer age) {
        return userService.findUsersByAgeGreaterThan(age);
    }

    @GetMapping("/users/color")
    public List<User> getUsersByArticlesColor(@RequestParam(defaultValue = "UNDEFINED") Color color) {
        return userService.findAllByArticlesColor(color);
    }

    @GetMapping("/users/articlesCount")
    public List<String> getUsersByCountOfArticles() {
        return userService.findAllByCountOfArticles();
    }
}
