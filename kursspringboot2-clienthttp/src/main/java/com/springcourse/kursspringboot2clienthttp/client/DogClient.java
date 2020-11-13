package com.springcourse.kursspringboot2clienthttp.client;

import com.springcourse.kursspringboot2clienthttp.api.Dog;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Stream;

@Controller
public class DogClient {
    private RestTemplate restTemplate;

    public DogClient() {
        this.restTemplate = new RestTemplate();
        getDogs();
    }

    @EventListener(ApplicationReadyEvent.class)
    private void getDogs() {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("amount", "2");

        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<Dog[]> exchange = restTemplate.exchange("http://localhost:8080/dogs/",
                HttpMethod.GET,
                httpEntity,
                Dog[].class);

        Stream.of(exchange.getBody()).forEach(System.out::println);
    }
}
