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

@Route("show1-car")
public class CarShowOneCarGui extends VerticalLayout {

    private CarRepository carRepository;
    private CarService service;

    @Autowired
    public CarShowOneCarGui(CarRepository carRepository, CarService service) {
        this.carRepository = carRepository;
        this.service = service;

        Details componentInfo = new Details("SHOW CAR BY ID",
                new Text("Enter id of the car and press button:"));
        TextField textFieldId = new TextField("Id");
        Button buttonRemove = new Button("Show Car");
        Dialog dialog = new Dialog();

        buttonRemove.addClickListener(clickEvent -> {
            Car car = carRepository.findCarById(service.convertToLong(textFieldId.getValue()));
            if (car != null){
                dialog.add(new Text(car.getId() + ":  " + car.getMark() + " " + car.getModel() + " " + car.getColor()));
                dialog.open();
            }else{
                dialog.add(new Text("Sorry, there is no id " + textFieldId.getValue() + "!"));
                dialog.open();
            }
        });
        add(componentInfo, textFieldId, buttonRemove);
    }
}
