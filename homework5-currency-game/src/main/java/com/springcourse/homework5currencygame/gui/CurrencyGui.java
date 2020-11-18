package com.springcourse.homework5currencygame.gui;

import com.springcourse.homework5currencygame.controller.CurrencyController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route
public class CurrencyGui extends VerticalLayout {

    private CurrencyController currencyController;
    String nameCurrency;
    double valueRandomCurrency;
    int counter = 1;

    @Autowired
    public CurrencyGui(CurrencyController currencyController) {
        this.currencyController = currencyController;

        nameCurrency = currencyController.getCurrencyName().get(currencyController.getRandomIndex());
        valueRandomCurrency = currencyController.getCurrencyMap().get(nameCurrency);
        double roundedValueRandomCurrency = Math.round((1/(valueRandomCurrency)) * 100.0) / 100.0;

        Dialog dialogWelcome = new Dialog();
        dialogWelcome.add(new Text("NEW GAME - TRY TO GUESS THE EXCHANGE RATE!"));
        dialogWelcome.setWidth("450px");
        dialogWelcome.setHeight("100px");
        dialogWelcome.open();

        Label label = new Label();
        label.add("Try to guess the exchange rate:");

        TextField readonlyField = new TextField();
        readonlyField.setValue("1 " + nameCurrency);
        readonlyField.setLabel("Our currency:");
        readonlyField.setReadOnly(true);

        Icon logo = new Icon(VaadinIcon.MONEY_EXCHANGE);
        logo.setSize("100px");
        logo.setColor("blue");

        TextField labelField = new TextField();
        labelField.setLabel("PLN:");

        Button button = new Button("OK");
        button.addClickListener(clickEvent -> {
                try {
                    double userInput = Double.parseDouble(labelField.getValue());
                    double roundedDouble = Math.round(userInput * 100.0) / 100.0;
                    currencyController.setUserGuess(roundedDouble);

                    if (userInput < roundedValueRandomCurrency){
                        Dialog dialog = new Dialog();
                        dialog.add(new Text("SORRY, NOT ENOUGH :("));
                        dialog.setWidth("250px");
                        dialog.setHeight("100px");
                        dialog.open();
                        counter++;
                    }else if (userInput > roundedValueRandomCurrency){
                        Dialog dialog = new Dialog();
                        dialog.add(new Text("SORRY, TOO MUCH :("));
                        dialog.setWidth("220px");
                        dialog.setHeight("100px");
                        dialog.open();
                        counter++;
                    }else{
                        Dialog dialog = new Dialog();
                        dialog.add(new Text("CONGATULATIONS! Tries quantity: " + counter + " "),
                                new Button("Close", e -> UI.getCurrent().getPage().reload()));
                        dialog.setWidth("400px");
                        dialog.setHeight("100px");
                        dialog.open();
                    }
                } catch (Exception e) {
                    Label error = new Label();
                    error.add(labelField.getValue() + " is not o number! Try again (e.g. 4.23)!");
                    setHorizontalComponentAlignment(Alignment.CENTER, error);
                    add(error);
                    counter++;
                }
        });

        setHorizontalComponentAlignment(Alignment.CENTER, label, logo, readonlyField, labelField, button);
        add(label, readonlyField, logo, labelField, button);
        }
}
