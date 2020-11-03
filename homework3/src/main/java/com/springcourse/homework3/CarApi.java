package com.springcourse.homework3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarApi {
    private List<Car> carList;

    public CarApi() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Wartburg", "353","Blue"));
        carList.add(new Car(2L, "Trabant", "601 deluxe","White"));
        carList.add(new Car(3L, "Syrena", "Sport","Blue"));
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity(carList, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getCarById(){
        return new ResponseEntity(carList, HttpStatus.OK);
    }
}
