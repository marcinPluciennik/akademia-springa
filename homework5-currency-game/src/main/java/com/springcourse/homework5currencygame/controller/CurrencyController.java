package com.springcourse.homework5currencygame.controller;

import com.springcourse.homework5currencygame.model.Currency;
import com.springcourse.homework5currencygame.model.Rates;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CurrencyController {
    private RestTemplate restTemplate;
    private Map<String, Double> currencyMap;
    private double userGuess;

    public CurrencyController() {
        this.restTemplate = new RestTemplate();

        currencyMap = new HashMap<>();
        currencyMap.put("CAD", getRates().getCAD());
        currencyMap.put("HKD", getRates().getHKD());
        currencyMap.put("ISK", getRates().getISK());
        currencyMap.put("PHP", getRates().getPHP());
        currencyMap.put("DKK", getRates().getDKK());
        currencyMap.put("HUF", getRates().getHUF());
        currencyMap.put("CZK", getRates().getCZK());
        currencyMap.put("GBP", getRates().getGBP());
        currencyMap.put("RON", getRates().getRON());
        currencyMap.put("SEK", getRates().getSEK());
        currencyMap.put("IDR", getRates().getIDR());
        currencyMap.put("INR", getRates().getINR());
        currencyMap.put("BRL", getRates().getBRL());
        currencyMap.put("RUB", getRates().getRUB());
        currencyMap.put("HRK", getRates().getHRK());
        currencyMap.put("JPY", getRates().getJPY());
        currencyMap.put("THB", getRates().getTHB());
        currencyMap.put("EUR", getRates().getEUR());
        currencyMap.put("MYR", getRates().getMYR());
        currencyMap.put("BGN", getRates().getBGN());
        currencyMap.put("TRY", getRates().getTRY());
        currencyMap.put("CNY", getRates().getCNY());
        currencyMap.put("NOK", getRates().getNOK());
        currencyMap.put("NZD", getRates().getNZD());
        currencyMap.put("ZAR", getRates().getZAR());
        currencyMap.put("CHF", getRates().getCHF());
        currencyMap.put("USD", getRates().getUSD());
        currencyMap.put("MXN", getRates().getMXN());
        currencyMap.put("SGD", getRates().getSGD());
        currencyMap.put("AUD", getRates().getAUD());
        currencyMap.put("ILS", getRates().getILS());
        currencyMap.put("KRW", getRates().getKRW());
        currencyMap.put("PLN", getRates().getPLN());

    }

    public Currency getCurrency(){
        Currency currency = restTemplate.getForObject("https://api.exchangeratesapi.io/latest?base=PLN",
                Currency.class);
        return currency;
    }

    public Rates getRates(){
        Rates rates = getCurrency().getRates();
        return rates;
    }

    public List<String> getCurrencyName(){
        List<String> currencyNames = new ArrayList<>();
        for (Map.Entry<String,Double> entry: currencyMap.entrySet()){
            currencyNames.add(entry.getKey());
        }
        return currencyNames;
    }

    public int getRandomIndex(){
        int randomIndex = Integer.valueOf(ThreadLocalRandom.current().nextInt(0,getCurrencyName().size()));
        return randomIndex;
    }

    public Map<String, Double> getCurrencyMap() {
        return currencyMap;
    }

    public void setCurrencyMap(Map<String, Double> currencyMap) {
        this.currencyMap = currencyMap;
    }

    public double getUserGuess() {
        return userGuess;
    }

    public void setUserGuess(double userGuess) {
        this.userGuess = userGuess;
    }
}
