package com.springcourse.kursspringboot2vaadin.gui;

import com.springcourse.kursspringboot2vaadin.manager.CarManager;
import com.springcourse.kursspringboot2vaadin.model.Car;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("show-car")
public class CarShowGui extends VerticalLayout {

    private CarManager carManager;

    @Autowired
    public CarShowGui(CarManager carManager) {
        this.carManager = carManager;

        Grid<Car> grid = new Grid<>(Car.class);
        grid.setItems(carManager.getCarList());

        add(grid);
    }
}
