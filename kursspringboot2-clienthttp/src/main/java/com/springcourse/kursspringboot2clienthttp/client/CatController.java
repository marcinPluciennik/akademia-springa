package com.springcourse.kursspringboot2clienthttp.client;

import com.springcourse.kursspringboot2clienthttp.api.AnimalFact;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CatController {
    public CatController() {
        RestTemplate restTemplate = new RestTemplate();
        AnimalFact[] forObject = restTemplate.getForObject("https://cat-fact.herokuapp.com/facts/random?animal_type=cat&amount=3",
                AnimalFact[].class);

        for (AnimalFact animalFact : forObject) {
            System.out.println(animalFact.getText());
        }
    }
}
