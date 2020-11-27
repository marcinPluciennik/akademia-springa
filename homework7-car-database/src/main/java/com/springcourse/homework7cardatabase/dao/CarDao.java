package com.springcourse.homework7cardatabase.dao;

import com.springcourse.homework7cardatabase.model.Car;

import java.util.List;

public interface CarDao {
    void saveCar(Car car);
    List<Car> findAll();
    List<Car> findByYearsRange(int yearStart, int yearEnd);
    Car findById(long id);
    void updateCar(Car newCar);
    void deleteCar(long id);
}
