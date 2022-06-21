package com.example.testtask.controller;

import com.example.testtask.model.User;
import com.example.testtask.repository.UserRepository;
import com.example.testtask.service.UserService;
import com.example.testtask.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private UserController controller;
    private UserService userService;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
        controller = new UserController(userService);
    }

    @Test
    public void whenSaveUserThenShouldCallRepositoryMethod() {
        //GIVEN
        User user = createUser("Hanna", 21);
        //WHEN
        controller.saveUser(user);
        //THEN
        verify(userRepository).save(user);
    }

    @Test
    public void whenSaveUserWithNullNameThenThrownException() {
        //GIVEN
        User user = createUser(null, 22);
        //THEN
        assertThatThrownBy(() -> controller.saveUser(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User name can`t be null");
    }

    @Test
    public void whenSaveUserWithInvalidNameThenThrownException() {
        //GIVEN
        User user = createUser("a", 22);
        //THEN
        assertThatThrownBy(() -> controller.saveUser(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User name length must be greater then 2");
    }

    @Test
    public void whenSaveUserWithInvalidAgeThenThrownException() {
        //GIVEN
        User user = createUser("Hanna", -1);
        //THEN
        assertThatThrownBy(() -> controller.saveUser(user))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("User age must be greater then 0");
    }

    @Test
    public void whenGetUserByAgeThenShouldCallRepositoryMethod() {
        //WHEN
        int age = 18;
        controller.getUsersByAge(age);
        //THEN
        verify(userRepository).findUsersByAgeGreaterThan(age);
    }

    @Test
    public void whenAgeIsNullThenThrownException() {
        assertThatThrownBy(() -> controller.getUsersByAge(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Age can`t be null");
    }

    @Test
    public void whenAgeIsZeroThenThrownException() {
        int age = 0;
        assertThatThrownBy(() -> controller.getUsersByAge(age))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Age can`t be " + age);
    }

    private User createUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
