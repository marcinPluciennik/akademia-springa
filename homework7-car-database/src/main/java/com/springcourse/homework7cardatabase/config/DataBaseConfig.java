package com.springcourse.homework7cardatabase.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    private DataSource dataSource;

    @Autowired
    public DataBaseConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate(){
        return new JdbcTemplate(dataSource);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void initCarTable(){
//        String sql = "CREATE TABLE cars(" +
//                "car_id int, mark varchar(255), model varchar(255), color varchar(255), year int, " +
//                "PRIMARY KEY(car_id))";
//        getJdbcTemplate().update(sql);
//    }
}
