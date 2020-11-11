package com.springcourse.homework4vaadin.controller;

import com.springcourse.homework4vaadin.model.Car;
import com.springcourse.homework4vaadin.repository.CarRepository;
import com.springcourse.homework4vaadin.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarApi {

    @Autowired
    private CarRepository carList;

    @Autowired
    private CarService carService;

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity(carList, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Optional<Car> carById = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();

        if (carById.isPresent()){
            return new ResponseEntity<>(carById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color){
        List<Car> carsByColor = carList.getCarList().stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());

        return new ResponseEntity<>(carsByColor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        boolean isAdded = carList.getCarList().add(car);
        if (isAdded){
            return new ResponseEntity(car, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping
    public ResponseEntity editCar(@RequestBody Car newCar){
        Optional<Car> carById = carList.getCarList().stream()
                .filter(car -> car.getId() == newCar.getId())
                .findFirst();
        if (carById.isPresent()){
            carList.getCarList().remove(carById.get());
            carList.getCarList().add(newCar);
            return new ResponseEntity(carList.getCarList().get(carList.getCarList().size() - 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity removeCarById(@PathVariable Long id){
        Optional<Car> carById = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (carById.isPresent()){
            carList.getCarList().remove(carById.get());
            return new ResponseEntity<>(carById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/id/{id}")
    public ResponseEntity editOneParameterOfCar(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        Optional<Car> carById = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (carById.isPresent()){
            carService.updateOneParameterOfCar(carById.get(), updates);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
