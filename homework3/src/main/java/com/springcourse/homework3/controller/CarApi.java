package com.springcourse.homework3.controller;

import com.springcourse.homework3.domain.Car;
import com.springcourse.homework3.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public CarRepository carList;

    @GetMapping("/getCars")
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity(carList, HttpStatus.OK);
    }

    @GetMapping("/getCarById/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Optional<Car> foundCar = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (foundCar.isPresent()){
            return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getCarsByColor/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color){
        List<Car> foundCars = carList.getCarList().stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
        if (foundCars.size() > 0){
            return new ResponseEntity<>(foundCars, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping("/addCar")
    public ResponseEntity addCar(@RequestBody Car car){
        boolean isAdded = carList.getCarList().add(car);
        if (isAdded){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/editCar")
    public ResponseEntity editCar(@RequestBody Car newCar){
        Optional<Car> foundCar = carList.getCarList().stream()
                .filter(car -> car.getId() == newCar.getId())
                .findFirst();
        if (foundCar.isPresent()){
            carList.getCarList().remove(foundCar.get());
            carList.getCarList().add(newCar);
            return new ResponseEntity(carList.getCarList().get(carList.getCarList().size() - 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/removeCarById/{id}")
    public ResponseEntity removeCarById(@PathVariable Long id){
        Optional<Car> foundCar = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (foundCar.isPresent()){
            carList.getCarList().remove(foundCar.get());
            return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
