package com.springcourse.homework8weatherhibernate.controller;

import com.springcourse.homework8weatherhibernate.model.ConsolidatedWeather;
import com.springcourse.homework8weatherhibernate.model.Temp;
import com.springcourse.homework8weatherhibernate.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Controller
public class TempController {
    private RestTemplate restTemplate;


    @Autowired
    public TempController() {
        this.restTemplate = new RestTemplate();
    }

    public Weather getWeatherInCity(){
        Weather weatherInLondon = restTemplate.getForObject("https://www.metaweather.com/api/location/44418/",
                Weather.class);
        return weatherInLondon;
    }

    public ConsolidatedWeather getConsolidatedWeather(){
        ConsolidatedWeather consolidatedWeather = getWeatherInCity().getConsolidatedWeather().get(0);
        return consolidatedWeather;
    }

    public Temp getLondonTemp(){
        Temp temp = new Temp(LocalTime.now(), getConsolidatedWeather().getTheTemp());
        System.out.println(temp);
        return temp;
    }
}
