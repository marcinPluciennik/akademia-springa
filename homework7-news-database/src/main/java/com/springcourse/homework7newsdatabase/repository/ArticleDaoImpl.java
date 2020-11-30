package com.springcourse.homework7newsdatabase.repository;

import com.springcourse.homework7newsdatabase.dao.ArticleDao;
import com.springcourse.homework7newsdatabase.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ArticleDaoImpl implements ArticleDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void saveNews(List<Article> articles) {
        String sql = "INSERT INTO articles VALUES (?,?,?,?,?,?,?,?)";
        for (int i = 0; i < articles.size(); i++){
            jdbcTemplate.update(sql, articles.get(i).setArticle_id(i+1), articles.get(i).getAuthor(),
                    articles.get(i).getTitle(), articles.get(i).getDescription(),
                    articles.get(i).getUrl(), articles.get(i).getUrlToImage(),
                    articles.get(i).getPublishedAt(), articles.get(i).getContent());
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();
        String sql = "SELECT * FROM articles";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> articleList.add(new Article(
                        Long.parseLong(String.valueOf(element.get("article_id"))),
                        String.valueOf(element.get("author")),
                        String.valueOf(element.get("title")),
                        String.valueOf(element.get("description")),
                        String.valueOf(element.get("url")),
                        String.valueOf(element.get("urlToImage")),
                        String.valueOf(element.get("publishedAt")),
                        String.valueOf(element.get("content")))));
        return articleList;
    }


    @Override
    public void updateArticle(Article newArticle) {
        String sql = "UPDATE articles SET articles.author=?, articles.title=?, articles.description=?, " +
                "articles.url=?, articles.urlToImage=?, articles.publishedAt=?, " +
                "articles.content=? WHERE articles.article_id=?";
        jdbcTemplate.update(sql, newArticle.getAuthor(), newArticle.getTitle(), newArticle.getDescription(),
                newArticle.getUrl(), newArticle.getUrlToImage(), newArticle.getPublishedAt(),
                newArticle.getContent(), newArticle.getArticle_id());
    }

    @Override
    public void saveArticle(long article_id, String author, String title, String description, String url,
                            String urlToImage, String publishedAt, String content) {
        Article article1 = new Article(article_id, author, title, description, url,
                urlToImage, publishedAt, content);
        String sql = "INSERT INTO articles VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, article1.getArticle_id(), article1.getAuthor(), article1.getTitle(),
                article1.getDescription(), article1.getUrl(), article1.getUrlToImage(), article1.getPublishedAt(),
                article1.getContent());
    }

    @Override
    public boolean deleteArticle(long id) {
        Optional<Article> articleOptional = findAll().stream()
                .filter(a -> a.getArticle_id() == id)
                .findFirst();
        if (articleOptional.isPresent()){
            String sql = "DELETE FROM articles WHERE articles.article_id = ?";
            jdbcTemplate.update(sql, id);
            return true;
        }
        return false;
    }

    @Override
    public Article findArticle(long id) {
        String sql = "SELECT * FROM articles WHERE articles.article_id = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, column) -> new Article(
                resultSet.getLong("article_id"),
                resultSet.getString("author"),
                resultSet.getString("title"),
                resultSet.getString("description"),
                resultSet.getNString("url"),
                resultSet.getString("urlToImage"),
                resultSet.getString("publishedAt"),
                resultSet.getString("content")),id);
    }
}
