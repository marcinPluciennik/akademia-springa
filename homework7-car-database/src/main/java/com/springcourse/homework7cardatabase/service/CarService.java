package com.springcourse.homework7cardatabase.service;

import org.springframework.stereotype.Service;

@Service
public class CarService {

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }

    public Integer convertToInt(Object o){
        String stringToConvert = String.valueOf(o);
        Integer convertedInt = Integer.parseInt(stringToConvert);
        return convertedInt;
    }
}
