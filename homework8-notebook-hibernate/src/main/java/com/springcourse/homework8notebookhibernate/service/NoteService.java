package com.springcourse.homework8notebookhibernate.service;

import org.springframework.stereotype.Service;

@Service
public class NoteService {

    public static Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
