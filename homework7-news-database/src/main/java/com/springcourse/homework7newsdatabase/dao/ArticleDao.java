package com.springcourse.homework7newsdatabase.dao;

import com.springcourse.homework7newsdatabase.model.Article;

import java.util.List;

public interface ArticleDao {
    void saveNews(List<Article> articles);
    List<Article> findAll();
    void updateArticle(Article newArticle);
    void saveArticle(long article_id, String author, String title, String description, String url,
                     String urlToImage, String publishedAt, String content);
    Article findArticle(long id);
    boolean deleteArticle(long id);
}
