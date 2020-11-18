package com.springcourse.homework52.controller;

import com.springcourse.homework52.model.City;
import com.springcourse.homework52.model.ConsolidatedWeather;
import com.springcourse.homework52.model.WeatherInCity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;


@Controller
public class WeatherController {
    private String cityName;
    private RestTemplate restTemplate;

    public WeatherController() {
        this.restTemplate = new RestTemplate();
    }

    public City[] getCities(){
        City[] cities =  restTemplate.getForObject("https://www.metaweather.com/api/location/search/?query=" +
                cityName, City[].class);
        return cities;
    }

    public Integer getWoeidOneCity(){
        Integer woeid = getCities()[0].getWoeid();
        return woeid;
    }

    public WeatherInCity getWeatherInCity(){
        WeatherInCity weatherInCity = restTemplate.getForObject("https://www.metaweather.com/api/location/" + getWoeidOneCity(),
                WeatherInCity.class);
        return weatherInCity;
    }

    public ConsolidatedWeather getConsolidatedWeather(){
        ConsolidatedWeather consolidatedWeather = getWeatherInCity().getConsolidatedWeather().get(0);
        return consolidatedWeather;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
