package com.springcourse.homework7newsdatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DbConfig {
    private DataSource dataSource;

    @Autowired
    public DbConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void initArticleTable(){
//        String sql = "CREATE TABLE articles(" +
//                "article_id int, author varchar(255), title varchar(255), description varchar(1000), " +
//                "url varchar(255), urlToImage varchar(255), publishedAt varchar(255)," +
//                "content varchar(1000), PRIMARY KEY(article_id))";
//        getJdbcTemplate().update(sql);
//    }
}
