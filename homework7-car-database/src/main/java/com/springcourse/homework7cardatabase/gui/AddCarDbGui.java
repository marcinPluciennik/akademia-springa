package com.springcourse.homework7cardatabase.gui;

import com.springcourse.homework7cardatabase.controller.CarController;
import com.springcourse.homework7cardatabase.model.Car;
import com.springcourse.homework7cardatabase.service.CarService;
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
public class AddCarDbGui extends VerticalLayout {

    private CarController carController;
    private CarService service;

    @Autowired

    public AddCarDbGui(CarController carController, CarService service) {
        this.carController = carController;
        this.service = service;


        Details componentInfo = new Details("ADD NEW CAR TO THE LIST",
                new Text("Fill the form below and press button:"));
        TextField textFieldId = new TextField("Id");
        TextField textFieldMark = new TextField("Mark");
        TextField textFieldModel = new TextField("Model");
        TextField textFieldColor = new TextField("Color");
        TextField textFieldYear = new TextField("Year");
        Button buttonAdd = new Button("Add Car");
        Dialog dialog = new Dialog();

        buttonAdd.addClickListener(clickEvent -> {
            Car car = new Car(service.convertToLong(textFieldId.getValue()), textFieldMark.getValue(),
                    textFieldModel.getValue(), textFieldColor.getValue().toUpperCase(),
                    service.convertToInt(textFieldYear.getValue()));

            Optional<Car> carById = carController.getCars().getBody().stream()
                    .filter(c -> c.getCarId() == service.convertToLong(textFieldId.getValue()))
                    .findFirst();
            if (carById.isPresent()){
                dialog.add(new Text("ERROR, car with id: " + textFieldId.getValue() + " exists already!"));
                dialog.open();
            }else{
                carController.addCar(car);
                dialog.add(new Text("The Car has been added!"));
                dialog.open();
            }
        });
        add(componentInfo, textFieldId, textFieldMark, textFieldModel, textFieldColor, textFieldYear, buttonAdd);
    }
}
