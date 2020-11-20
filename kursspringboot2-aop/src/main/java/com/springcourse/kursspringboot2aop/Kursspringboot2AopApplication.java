package com.springcourse.kursspringboot2aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Kursspringboot2AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(Kursspringboot2AopApplication.class, args);
    }

}
