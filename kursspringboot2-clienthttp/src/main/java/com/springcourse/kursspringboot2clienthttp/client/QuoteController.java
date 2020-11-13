package com.springcourse.kursspringboot2clienthttp.client;

import com.springcourse.kursspringboot2clienthttp.api.Dog;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Controller
public class QuoteController {

    private RestTemplate restTemplate;

    public QuoteController() {
        this.restTemplate = new RestTemplate();

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("user-agent", "Mozilla/5.0 Firefox/26.0");

        HttpEntity httpEntity = new HttpEntity(headers);

        String exchange = restTemplate.exchange("https://api.kanye.rest/",
                HttpMethod.GET,
                httpEntity,
                String.class).getBody();
        //String forObject = restTemplate.getForObject("https://api.kanye.rest/", String.class);
        System.out.println(exchange);
    }
}
