package com.springcourse.homework4vaadin.gui;

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

@Route("remove-car")
public class CarRemoveGui extends VerticalLayout {

    private CarRepository carRepository;
    private CarService service;

    @Autowired
    public CarRemoveGui(CarRepository carRepository, CarService service) {
        this.carRepository = carRepository;
        this.service = service;

        Details componentInfo = new Details("REMOVE CAR FROM THE LIST",
                new Text("Enter car id and press button:"));
        TextField textFieldId = new TextField("Id");
        Button buttonRemove = new Button("Remove Car");
        Dialog dialog = new Dialog();

        buttonRemove.addClickListener(clickEvent -> {
            boolean result = carRepository.removeCarById(service.convertToLong(textFieldId.getValue()));
            if (result){
                dialog.add(new Text("Car has been removed!"));
                dialog.open();
            }else{
                dialog.add(new Text("ERROR, there is no id " + textFieldId.getValue() + "!"));
                dialog.open();
            }
        });
        add(componentInfo, textFieldId, buttonRemove);
    }
}
