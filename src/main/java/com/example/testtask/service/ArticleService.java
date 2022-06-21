package com.example.testtask.service;

import com.example.testtask.model.Article;
import com.example.testtask.repository.ArticleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) {
        filterArticle(article);
        return articleRepository.save(article);
    }

    private void filterArticle(Article article) {
        if(article.getColor() == null || article.getColor().name().equals("UNDEFINED")){
            throw new IllegalArgumentException("Color can`t be " + article.getColor());
        }
        if(article.getUserId() == null){
            throw new IllegalArgumentException("User id can`t be null");
        }
    }
}
