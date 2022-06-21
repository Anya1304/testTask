package com.example.testtask.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String text;
    private Color color;
    @ManyToOne
    @JoinColumn(name = "user_id",  insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private Integer userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id
                && Objects.equals(text, article.text)
                && color == article.color
                && Objects.equals(user, article.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, color, user);
    }
}
