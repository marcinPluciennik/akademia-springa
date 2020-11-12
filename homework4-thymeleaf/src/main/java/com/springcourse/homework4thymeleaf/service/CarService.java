package com.springcourse.homework4thymeleaf.service;

import com.springcourse.homework4thymeleaf.model.Car;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CarService {
    public void updateOneParameterOfCar(Car car, Map<String, Object> updates){
        if (updates.containsKey("mark")){
            car.setMark((String) updates.get("mark"));
        }
        if (updates.containsKey("model")){
            car.setModel((String) updates.get("model"));
        }
        if (updates.containsKey("color")){
            car.setColor((String) updates.get("color"));
        }
        if (updates.containsKey("id")){
            car.setId((convertToLong(updates.get("id"))));
        }
    }

    public Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
