package com.springcourse.kursspringboot2vaadin.manager;

import com.springcourse.kursspringboot2vaadin.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarManager {
    private List<Car> carList;

    public CarManager() {
        carList = new ArrayList<>();
        carList.add(new Car("Fiat", "126p"));
        carList.add(new Car("BMW", "X1"));
    }

    public void addCar(Car car){
        carList.add(car);
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }
}
