package com.springcourse.homework4thymeleaf.controller;

import com.springcourse.homework4thymeleaf.model.Car;
import com.springcourse.homework4thymeleaf.repository.CarRepository;
import com.springcourse.homework4thymeleaf.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarController {

    private CarRepository carList;
    private CarService carService;

    @Autowired
    public CarController(CarRepository carList, CarService carService) {
        this.carList = carList;
        this.carService = carService;
    }

    @GetMapping("/car")
    public String getCars(Model model) {
        model.addAttribute("carList", carList);
        model.addAttribute("newCar", new Car());
        return "addCar";
    }

    @PostMapping("/add-car")
    public String addCar(@ModelAttribute Car car) {
        carList.getCarList().add(car);
        return "redirect:/car";
    }
}
