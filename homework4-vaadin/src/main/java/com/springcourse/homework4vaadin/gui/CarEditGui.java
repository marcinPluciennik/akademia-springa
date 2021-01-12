package com.springcourse.homework4vaadin.gui;

import com.springcourse.homework4vaadin.model.Car;
import com.springcourse.homework4vaadin.repository.CarRepository;
import com.springcourse.homework4vaadin.service.CarService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("edit-car")
public class CarEditGui extends VerticalLayout {

    private CarRepository carRepository;
    private CarService service;

    @Autowired
    public CarEditGui(CarRepository carRepository, CarService service) {
        this.carRepository = carRepository;
        this.service = service;

        Details componentInfo = new Details("EDIT CAR",
                new Text("Fill the form below and press button:"));
        TextField textFieldId = new TextField("Id");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        TextField textFieldColor = new TextField("Color");
        Button buttonAdd = new Button("Edit Car");
        Dialog dialog = new Dialog();

    }
}
