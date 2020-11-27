package com.springcourse.homework7cardatabase.repository;

import com.springcourse.homework7cardatabase.dao.CarDao;
import com.springcourse.homework7cardatabase.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CarDaoImpl implements CarDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveCar(Car car) {
        String sql = "INSERT INTO cars VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql, car.getCarId(), car.getMark(),
                car.getModel(), car.getColor(), car.getYear());
    }

    @Override
    public List<Car> findAll() {
        List<Car> carList = new ArrayList<>();
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.stream()
                .forEach(element -> carList.add(new Car(
                        Long.parseLong(String.valueOf(element.get("car_id"))),
                        String.valueOf(element.get("mark")),
                        String.valueOf(element.get("model")),
                        String.valueOf(element.get("color")),
                        Integer.parseInt(String.valueOf(element.get("year")))
                )));
        return carList;
    }

    @Override
    public List<Car> findByYearsRange(int yearStart, int yearEnd) {
        List<Car> carListByYearRange = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE cars.year >=? AND cars.year <=?";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql, yearStart, yearEnd);
        map.stream()
                .forEach(element -> carListByYearRange.add(new Car(
                        Long.parseLong(String.valueOf(element.get("car_id"))),
                        String.valueOf(element.get("mark")),
                        String.valueOf(element.get("model")),
                        String.valueOf(element.get("color")),
                        Integer.parseInt(String.valueOf(element.get("year")))
                )));
        return carListByYearRange;
    }

    @Override
    public Car findById(long id) {
        String sql = "SELECT * FROM cars WHERE cars.car_id = ?";
        return jdbcTemplate.queryForObject(sql, (resultSet, column) -> new Car(
                resultSet.getLong("car_id"),
                resultSet.getString("mark"),
                resultSet.getString("model"),
                resultSet.getString("color"),
                resultSet.getInt("year")), id);
    }

    @Override
    public void updateCar(Car newCar) {
        String sql = "UPDATE cars SET cars.mark=?, cars.model=?, cars.color=?, " +
                "cars.year=? WHERE cars.car_id=?";
        jdbcTemplate.update(sql, newCar.getMark(), newCar.getModel(), newCar.getColor(), newCar.getYear(), newCar.getCarId());
    }

    @Override
    public void deleteCar(long id) {
        String sql = "DELETE FROM cars WHERE cars.car_id = ?";
        jdbcTemplate.update(sql, id);
    }
}
