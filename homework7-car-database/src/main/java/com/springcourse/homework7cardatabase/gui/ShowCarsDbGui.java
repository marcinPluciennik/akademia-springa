package com.springcourse.homework7cardatabase.gui;

import com.springcourse.homework7cardatabase.controller.CarController;
import com.springcourse.homework7cardatabase.model.Car;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("show-cars")
public class ShowCarsDbGui extends VerticalLayout {

    private CarController carController;

    @Autowired
    public ShowCarsDbGui(CarController carController) {
        this.carController = carController;

        Details componentInfo = new Details("CAR LIST",
                new Text("All cars in our company:"));
        Grid<Car> grid = new Grid<>(Car.class);
        grid.setItems(carController.getCars().getBody());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("carId", "mark", "model", "color", "year");

        add(componentInfo, grid);
    }
}
