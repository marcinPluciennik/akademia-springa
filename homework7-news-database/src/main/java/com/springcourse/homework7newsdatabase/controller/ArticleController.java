package com.springcourse.homework7newsdatabase.controller;

import com.springcourse.homework7newsdatabase.model.Article;
import com.springcourse.homework7newsdatabase.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ArticleController {

    private RestTemplate restTemplate;

    @Autowired
    public ArticleController() {
        this.restTemplate = new RestTemplate();
    }

    public List<Article> getArticles(){
        News news = restTemplate.getForObject(
                "http://newsapi.org/v2/top-headlines?country=us&apiKey=f8929f310dd94ab0bd62bf41c939fb81",
                News.class);
        List<Article> articles = news.getArticles();
        return  articles;
    }
}
