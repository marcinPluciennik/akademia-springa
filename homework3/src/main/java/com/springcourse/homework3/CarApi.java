package com.springcourse.homework3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Optional<Car> foundCar = carList.stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (foundCar.isPresent()){
            return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/colors/{color}")
    public ResponseEntity<List<Car>> getCarByColor(@PathVariable String color){
        List<Car> foundCars = carList.stream()
                .filter(car -> car.getColor() == color)
                .collect(Collectors.toList());
        if (foundCars.size() > 0){
            return new ResponseEntity<>(foundCars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
