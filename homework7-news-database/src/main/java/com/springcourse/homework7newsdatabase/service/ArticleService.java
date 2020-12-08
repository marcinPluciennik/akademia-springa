package com.springcourse.homework7newsdatabase.service;

import com.springcourse.homework7newsdatabase.model.Article;
import com.springcourse.homework7newsdatabase.model.News;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ArticleService {

    private RestTemplate restTemplate;

    public ArticleService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Article> getArticles(){
        News news = restTemplate.getForObject(
                "http://newsapi.org/v2/top-headlines?country=us&apiKey=f8929f310dd94ab0bd62bf41c939fb81",
                News.class);
        List<Article> articles = news.getArticles();
        return  articles;
    }

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
