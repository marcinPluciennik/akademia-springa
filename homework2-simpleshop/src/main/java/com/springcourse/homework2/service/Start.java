package com.springcourse.homework2.service;

import com.springcourse.homework2.repository.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Profile("start")
public class Start {
    private Cart cart;

    @Value("${vat}")
    private BigDecimal vat;

    @Value("${discount}")
    private BigDecimal discount;

    @Autowired
    public Start(Cart cart) {
        this.cart = cart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printCartAndTotalPrice(){
        cart.getProductList().forEach(System.out::println);
        System.out.println("Total price (plus version): " + getTotalPrice() + " PLN");
    }

    public BigDecimal getTotalPrice(){
        BigDecimal sum = cart.getProductList().stream()
                .map(p -> p.getPrice().multiply(vat.divide(BigDecimal.valueOf(100)))
                        .multiply(discount.divide(BigDecimal.valueOf(100))).add(p.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }
}
