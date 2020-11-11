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


import java.util.Optional;

@Route("add-car")
public class CarAddGui extends VerticalLayout {

    private CarService service;

    private CarRepository carRepository;

    @Autowired
    public CarAddGui(CarRepository carRepository, CarService service) {
        this.carRepository = carRepository;
        this.service = service;

        Details componentInfo = new Details("ADD NEW CAR TO THE LIST",
                new Text("Fill the form below and press button:"));
        TextField textFieldId = new TextField("Id");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        TextField textFieldColor = new TextField("Color");
        Button buttonAdd = new Button("Add Car");
        Dialog dialog = new Dialog();

        buttonAdd.addClickListener(clickEvent -> {
            Car car = new Car(service.convertToLong(textFieldId.getValue()), textFieldMark.getValue(),
                    textFieldModel.getValue(), textFieldColor.getValue());

            Optional<Car> carById = carRepository.getCarList().stream()
                    .filter(c -> c.getId() == service.convertToLong(textFieldId.getValue()))
                    .findFirst();
            if (carById.isPresent()){
                dialog.add(new Text("ERROR, car with id: " + textFieldId.getValue() + " exists already!"));
                dialog.open();
            }else{
                carRepository.addCar(car);
                dialog.add(new Text("The Car has been added!"));
                dialog.open();
            }
        });
        add(componentInfo, textFieldId, textFieldMark, textFieldModel, textFieldColor, buttonAdd);
    }
}
