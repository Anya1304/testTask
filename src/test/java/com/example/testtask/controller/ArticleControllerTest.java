package com.example.testtask.controller;

import com.example.testtask.model.Article;
import com.example.testtask.model.Color;
import com.example.testtask.model.User;
import com.example.testtask.repository.ArticleRepository;
import com.example.testtask.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {
    private ArticleController articleController;
    private ArticleService articleService;
    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    void setUp() {
        articleService = new ArticleService(articleRepository);
        articleController = new ArticleController(articleService);
    }

    @Test
    public void whenSaveArticleThenShouldCallRepositoryMethod() {
        //GIVEN
        User user = createUser("Hanna", 21);
        Article article = createArticle("test", Color.GREEN, user);
        //WHEN
        articleController.saveArticle(article);
        //THEN
        verify(articleRepository).save(article);
    }

    @Test
    public void whenSaveArticleWithWrongColorThenThrownException() {
        //GIVEN
        User user = createUser("Hanna", 21);
        Article article = createArticle("test", Color.UNDEFINED, user);
        //THEN
        assertThatThrownBy(() -> articleController.saveArticle(article))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Color can`t be " + article.getColor());
    }

    @Test
    public void whenSaveArticleWithNullUserThenThrownException() {
        //GIVEN
        User user = createUser("Hanna", 21);
        Article article = createArticle("test", Color.UNDEFINED, null);
        //THEN
        assertThatThrownBy(() -> articleController.saveArticle(article))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Color can`t be " + article.getColor());
    }

    private Article createArticle(String text, Color color, User user) {
        Article article = new Article();
        article.setText(text);
        article.setColor(color);
        article.setUser(user);
        return article;
    }

    private User createUser(String name, int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        return user;
    }
}
