package com.springcourse.homework7cardatabase.gui;

import com.springcourse.homework7cardatabase.controller.CarController;
import com.springcourse.homework7cardatabase.model.Car;
import com.springcourse.homework7cardatabase.service.CarService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("show-cars-by-years")
public class ShowCarsDbByYearRangeGui extends VerticalLayout {

    private CarController carController;
    private CarService service;

    @Autowired
    public ShowCarsDbByYearRangeGui(CarController carController, CarService service) {
        this.carController = carController;
        this.service = service;

        Details componentInfo = new Details("FIND CARS IN THE RANGE OF YEARS:",
                new Text("Fill the form below and press button:"));
        TextField textFieldStartYear = new TextField("From year");
        TextField textFieldEndYear = new TextField("To year");
        Button buttonFind = new Button("Submit");

        buttonFind.addClickListener(clickEvent -> {
            List<Car> carList = carController.getCarsByYearRange(service.convertToInt(textFieldStartYear.getValue()),
                    service.convertToInt(textFieldEndYear.getValue())).getBody();

            Details componentInfo2 = new Details("CAR LIST FROM " +
                    textFieldStartYear.getValue() + " TO " + textFieldEndYear.getValue() + ":",
                    new Text("All cars in this range of years below"));

            if (carList == null) {
                Label label = new Label("SORRY, THERE ARE NO CARS FROM " +
                        textFieldStartYear.getValue() + " TO " + textFieldEndYear.getValue() + "!");
                add(label);
            }

            Grid<Car> grid = new Grid<>(Car.class);
            grid.setItems(carList);
            grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                    GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
            grid.setColumns("carId", "mark", "model", "color", "year");
            add(componentInfo2, grid);

        });

        add(componentInfo, textFieldStartYear, textFieldEndYear, buttonFind);
    }
}
