package com.springcourse.homework52.gui;

import com.springcourse.homework52.controller.WeatherController;
import com.springcourse.homework52.model.City;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route
public class WeatherGui extends VerticalLayout {

    private WeatherController weatherController;

    @Autowired
    public WeatherGui(WeatherController weatherController) {
        this.weatherController = weatherController;

        Details component = new Details("Fill the form below and check the weather:",new Text(""));
        TextField placeholderField = new TextField();
        placeholderField.setPlaceholder("Your city");
        Button button = new Button("Confirm");


        button.addClickListener(clickEvent -> {
            weatherController.setCityName(placeholderField.getValue().toLowerCase());

            if (weatherController.getCities().length == 0){
                Dialog dialog = new Dialog();
                dialog.add(new Text("Sorry, there is no city "
                        + placeholderField.getValue() + " in our database:( Try again."));
                dialog.setWidth("700px");
                dialog.setHeight("50px");
                dialog.open();
            }else if (weatherController.getCities().length == 1){
                Label label = new Label(placeholderField.getValue().toUpperCase() + ",  "
                        + weatherController.getWeatherInCity().getParent().getTitle());
                label.getStyle().set("color", "blue");
                label.getStyle().set("fontWeight","bold");
                label.getStyle().set("font-size", "30px");


                Image image = new Image("https://www.metaweather.com/static/img/weather/" +
                        weatherController.getConsolidatedWeather().getWeatherStateAbbr() + ".svg", "WeatherImage");
                image.setWidth("200px");
                image.setHeight("200px");

                Tabs tabs = new Tabs(
                        new Tab("WEATHER STATE NAME : "
                                + weatherController.getConsolidatedWeather().getWeatherStateName()),
                        new Tab("TEMPERATURE [C]    : "
                                + String.format("%.0f", weatherController.getConsolidatedWeather().getTheTemp())),
                        new Tab("WIND SPEED [mph]   : "
                                + String.format("%.0f", weatherController.getConsolidatedWeather().getWindSpeed())),
                        new Tab("AIR PRESSURE [hPa] : "
                                + String.format("%.0f", weatherController.getConsolidatedWeather().getAirPressure())));
                tabs.setOrientation(Tabs.Orientation.VERTICAL);
                tabs.setHeight("150px");
                setHorizontalComponentAlignment(Alignment.CENTER, label, image, tabs);
                add(label, image, tabs);
            }else{
                Label label = new Label("Could you be more specific? I found more than one city:");
                label.getStyle().set("color", "blue");
                label.getStyle().set("font-size", "30px");

                Label labelCities = new Label();
                for (City c: weatherController.getCities()){
                    labelCities.add(" | " + c.getTitle() + " | ");
                }
                setHorizontalComponentAlignment(Alignment.CENTER, label, labelCities);
                add(label, labelCities);
            }
        });
        add(component, placeholderField, button);
    }
}
