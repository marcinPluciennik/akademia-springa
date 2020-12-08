package com.springcourse.homework7newsdatabase.controller;

import com.springcourse.homework7newsdatabase.dao.ArticleDao;
import com.springcourse.homework7newsdatabase.model.Article;
import com.springcourse.homework7newsdatabase.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleRestController {

    private ArticleDao articleDao;
    private ArticleService service;

    @Autowired
    public ArticleRestController(ArticleDao articleDao, ArticleService service) {
        this.articleDao = articleDao;
        this.service = service;
    }

    @GetMapping
    public List<Article> getArticles(){
        return articleDao.findAll();
    }

    @PostMapping
    public void saveArticles(){
        articleDao.saveNews(service.getArticles());
    }

    @PutMapping
    public void editArticle(@RequestBody Article newArticle){
        articleDao.updateArticle(newArticle);
    }

    @GetMapping("/id/{id}")
    public Article getArticleById(@PathVariable long id){
        return articleDao.findArticle(id);
    }

    @DeleteMapping("/id/{id}")
    public boolean removeArticleById(@PathVariable long id){
        return articleDao.deleteArticle(id);
    }

    @PostMapping("/addArticle")
    public void addArticle(@RequestBody Article article){
        articleDao.saveArticle(article.getArticle_id(), article.getAuthor(), article.getTitle(),
                article.getDescription(), article.getUrl(), article.getUrlToImage(),
                article.getPublishedAt(), article.getContent());
    }
}
