package com.springcourse.homework8weatherhibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Homework8WeatherHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(Homework8WeatherHibernateApplication.class, args);
    }

}
