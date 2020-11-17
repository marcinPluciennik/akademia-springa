package com.springcourse.homework51.controller;

import com.springcourse.homework51.model.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CryptoControllerVaadin {

    private RestTemplate restTemplate;

    @Autowired
    public CryptoControllerVaadin() {
        this.restTemplate = new RestTemplate();
    }

    public CryptoCurrency[] getCryptoCurrencies(){
        CryptoCurrency[] cryptoCurrencies = restTemplate.getForObject("https://api.coinpaprika.com/v1/coins/",
                CryptoCurrency[].class);
        return cryptoCurrencies;
    }
}
