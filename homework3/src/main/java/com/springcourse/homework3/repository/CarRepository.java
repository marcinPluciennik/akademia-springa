package com.springcourse.homework3.repository;

import com.springcourse.homework3.model.Car;
import com.springcourse.homework3.model.CarColor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
}
