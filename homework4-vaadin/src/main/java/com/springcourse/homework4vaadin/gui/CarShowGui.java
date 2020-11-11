package com.springcourse.homework4vaadin.gui;

import com.springcourse.homework4vaadin.model.Car;
import com.springcourse.homework4vaadin.repository.CarRepository;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("show-car")
public class CarShowGui extends VerticalLayout {

    private CarRepository carRepository;

    @Autowired
    public CarShowGui(CarRepository carRepository) {
        this.carRepository = carRepository;

        Details componentInfo = new Details("CAR LIST",
                new Text("All cars in our company:"));
        Grid<Car> grid = new Grid<>(Car.class);
        grid.setItems(carRepository.getCarList());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("id", "mark", "model", "color");

        add(componentInfo, grid);
    }
}
