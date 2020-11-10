package com.springcourse.kursspringboot2vaadin.gui;

import com.springcourse.kursspringboot2vaadin.manager.CarManager;
import com.springcourse.kursspringboot2vaadin.model.Car;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("add-car")
public class CarAdderGui extends VerticalLayout {

    private CarManager carManager;

    @Autowired
    public CarAdderGui(CarManager carManager) {
        this.carManager = carManager;

        TextField textFieldMark = new TextField();
        textFieldMark.setLabel("Mark");
        TextField textFieldModel = new TextField("Model");
        Button buttonAdd = new Button("Add car");

        buttonAdd.addClickListener(clickEvent -> {
            Car car = new Car(textFieldMark.getValue(), textFieldModel.getValue());
            carManager.addCar(car);

            carManager.getCarList().forEach(System.out::println);
        });

        add(textFieldMark);
        add(textFieldModel);
        add(buttonAdd);
    }
}
