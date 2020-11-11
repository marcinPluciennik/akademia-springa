package com.springcourse.homework4vaadin.repository;

import com.springcourse.homework4vaadin.model.Car;
import com.springcourse.homework4vaadin.model.CarColor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CarRepository {

    private List<Car> carList;

    public CarRepository() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Wartburg", "353", CarColor.BLUE.name()));
        carList.add(new Car(2L, "Trabant", "601 deluxe", CarColor.WHITE.name()));
        carList.add(new Car(3L, "Syrena", "Sport", CarColor.BLUE.name()));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public void addCar(Car car){
        carList.add(car);
    }

    public boolean removeCarById(Long id){
        Optional<Car> carById = carList.stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (carById.isPresent()){
            carList.remove(carById.get());
            return true;
        }
        return false;
    }

    public Car findCarById(Long id){
        Optional<Car> carById = carList.stream()
                .filter(car -> car.getId() == id)
                .findFirst();
        if (carById.isPresent()){
            return carById.get();
        }
        return null;
    }
}

