package com.springcourse.homework3.controller;

import com.springcourse.homework3.model.Car;
import com.springcourse.homework3.repository.CarRepository;
import com.springcourse.homework3.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/cars")
public class CarApi {

    @Autowired
    private CarRepository carList;

    @Autowired
    private CarService carService;

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CollectionModel<Car>> getCars(){
        carList.getCarList().forEach(car -> car.add(linkTo(CarApi.class).slash(car.getId()).withSelfRel()));
        Link link= linkTo(CarApi.class).withSelfRel();
        CollectionModel<Car> carEntityModels = CollectionModel.of(carList.getCarList(), link);

        return new ResponseEntity(carEntityModels, HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EntityModel<Car>> getCarById(@PathVariable Long id){
        Optional<Car> carById = carList.getCarList().stream()
                .filter(car -> car.getId() == id)
                .findFirst();

        if (carById.isPresent()){
            Link link = linkTo(CarApi.class).slash("id").slash(id).withSelfRel();
            EntityModel<Car> carEntityModel = EntityModel.of(carById.get(),link);
            return new ResponseEntity<>(carEntityModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/color/{color}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CollectionModel<Car>> getCarsByColor(@PathVariable String color){
        List<Car> carsByColor = carList.getCarList().stream()
                .filter(car -> car.getColor().equals(color))
                .collect(Collectors.toList());

        carsByColor.forEach(car -> car.add(linkTo(CarApi.class).slash("color").slash(car.getColor()).withSelfRel()));
        Link link = linkTo(CarApi.class).withSelfRel();
        CollectionModel<Car> carEntityModels = CollectionModel.of(carsByColor, link);

        return new ResponseEntity<>(carEntityModels, HttpStatus.OK);
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

    @DeleteMapping(value = "/id/{id}", produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE})
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
