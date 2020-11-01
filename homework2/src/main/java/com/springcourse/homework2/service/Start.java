package com.springcourse.homework2.service;

import com.springcourse.homework2.repository.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Start {
    private Cart cart;

    @Autowired
    public Start(Cart cart) {
        this.cart = cart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printCartAndTotalPrice(){
        System.out.println("Products in the cart:");
        cart.getProductList().forEach(System.out::println);
        BigDecimal sum = cart.getProductList().stream()
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total price: " + sum + " PLN");
    }
}
