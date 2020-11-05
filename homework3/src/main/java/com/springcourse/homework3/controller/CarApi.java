package com.springcourse.homework3.controller;

import com.springcourse.homework3.domain.Car;
import com.springcourse.homework3.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cars")
public class CarApi {

    @Autowired
    public CarRepository carList;

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity(carList, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Car> getCarById(@PathVariable Long id){
        Optional<Car> foundCar = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (foundCar.isPresent()){
            return new ResponseEntity<>(foundCar.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/color/{color}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Car>> getCarsByColor(@PathVariable String color){
        List<Car> foundCars = carList.getCarList().stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());
        if (foundCars.size() > 0){
            return new ResponseEntity<>(foundCars, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        boolean isAdded = carList.getCarList().add(car);
        if (isAdded){
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
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

    @DeleteMapping(value = "/id/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
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

    @PatchMapping("/id/{id}")
    public ResponseEntity editOneParameterOfCar(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        Optional<Car> foundCar = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (foundCar.isPresent()){
            updateOneParameterOfCar(foundCar.get(), updates);
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void updateOneParameterOfCar(Car car, Map<String, Object> updates){
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

    public static Long convertToLong(Object o){
        String stringToConvert = String.valueOf(o);
        Long convertedLong = Long.parseLong(stringToConvert);
        return convertedLong;
    }
}
