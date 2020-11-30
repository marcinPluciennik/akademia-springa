package com.springcourse.homework7newsdatabase.service;

import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
