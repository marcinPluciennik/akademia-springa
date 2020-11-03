package com.springcourse.homework3.repository;

import com.springcourse.homework3.domain.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CarRepository {
    private List<Car> carList;

    public CarRepository() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Wartburg", "353","Blue"));
        carList.add(new Car(2L, "Trabant", "601 deluxe","White"));
        carList.add(new Car(3L, "Syrena", "Sport","Blue"));
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
