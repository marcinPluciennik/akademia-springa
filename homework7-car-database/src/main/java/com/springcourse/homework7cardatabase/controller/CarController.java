package com.springcourse.homework7cardatabase.controller;

import com.springcourse.homework7cardatabase.dao.CarDao;
import com.springcourse.homework7cardatabase.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarDao carDao;

    @Autowired
    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(){
        return new ResponseEntity<>(carDao.findAll(), HttpStatus.OK);
    }

    @GetMapping("/carsByYearRange/{start}+{end}")
    public ResponseEntity<List<Car>> getCarsByYearRange(@PathVariable int start, @PathVariable int end){
        List<Car> cars = carDao.findByYearsRange(start, end);
        if (cars.size() > 0){
            return new ResponseEntity<>(cars, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/carById/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable long id){
        Optional<Car> carById = carDao.findAll().stream()
                .filter(car -> car.getCarId() == id)
                .findFirst();
        if (carById.isPresent()){
            return new ResponseEntity<>(carDao.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity addCar(@RequestBody Car car){
        try{
            carDao.saveCar(car);
            return new ResponseEntity(car, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity editCar(@RequestBody Car newCar){
        try {
            carDao.updateCar(newCar);
            return new ResponseEntity(newCar,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("removeCarById/{id}")
    public ResponseEntity removeCar(@PathVariable long id){
        Optional<Car> carById = carDao.findAll().stream()
                .filter(car -> car.getCarId() == id)
                .findFirst();
        if (carById.isPresent()){
            carDao.deleteCar(id);
            return new ResponseEntity<>(carById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
