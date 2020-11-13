package com.springcourse.kursspringboot2clienthttp.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class QuoteController {

    public QuoteController() {
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject("https://api.kanye.rest/", String.class);
        System.out.println(forObject);
    }
}
