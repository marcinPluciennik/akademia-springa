package com.springcourse.kursspringboot2hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



@Component
public class Start {

    private CarRepo carRepo;

    @Autowired
    public Start(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        Car carPolonez = new Car("Polonez", "Caro", Color.BLACK);
        Car carFiat = new Car("Fiat", "126p", Color.RED);
        Car carBmw = new Car("BMW", "123", Color.BLACK);
        Car carFiat2 = new Car("Fiat", "Panda", Color.RED);

        carRepo.save(carPolonez);
        carRepo.save(carFiat);
        carRepo.save(carBmw);
        carRepo.save(carFiat2);

        carRepo.findCarsByColorAndMark(Color.RED, "Fiat").forEach(System.out::println);
        carRepo.findCarsByModelMyImpl("Caro").forEach(System.out::println);

    }
}
