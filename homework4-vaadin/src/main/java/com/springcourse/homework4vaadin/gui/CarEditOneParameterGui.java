package com.springcourse.homework4vaadin.gui;

import com.springcourse.homework4vaadin.model.Car;
import com.springcourse.homework4vaadin.repository.CarRepository;
import com.springcourse.homework4vaadin.service.CarService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Route("edit1param-car")
public class CarEditOneParameterGui extends VerticalLayout {
    private CarRepository carRepository;
    private CarService service;

    @Autowired
    public CarEditOneParameterGui(CarRepository carRepository, CarService service) {
        this.carRepository = carRepository;
        this.service = service;

        Details componentInfo = new Details("EDIT ONE PARAMETER OF THE CAR",
                new Text("Fill the form below and press button:"));
        TextField textFieldId = new TextField("Id");
        ComboBox<String> labelComboBox = new ComboBox<>("Parameter");
        labelComboBox.setItems("id", "mark", "model", "color");
        TextField textFieldValue = new TextField("Value of parameter");
        Button buttonRemove = new Button("Edit parameter");
        Dialog dialog = new Dialog();

        buttonRemove.addClickListener(clickEvent -> {
            Car car = carRepository.findCarById(service.convertToLong(textFieldId.getValue()));
            if (car != null){
                Map<String, Object> updates = new HashMap<>();
                updates.put(labelComboBox.getValue(), textFieldValue.getValue());
                service.updateOneParameterOfCar(car, updates);
                dialog.add(new Text("Car has been edited!"));
                dialog.open();
            }else{
                dialog.add(new Text("Sorry, there is no id " + textFieldId.getValue() + "!"));
                dialog.open();
            }
        });
        add(componentInfo, textFieldId, labelComboBox, textFieldValue, buttonRemove);
    }
}
