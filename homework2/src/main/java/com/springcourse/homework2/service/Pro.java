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
@Profile("pro")
public class Pro {
    private Cart cart;

    @Value("${vat}")
    private BigDecimal vat;

    @Value("${discount}")
    private BigDecimal discount;

    @Autowired
    public Pro(Cart cart) {
        this.cart = cart;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void printCartAndTotalPrice(){
        cart.getProductList().forEach(System.out::println);
        System.out.println("Total price (pro version): " + getTotalPrice() + " PLN");
    }

    public BigDecimal getTotalPrice(){
        BigDecimal sum = cart.getProductList().stream()
                .map(p -> p.getPrice().add(p.getPrice().multiply(vat).divide(new BigDecimal(100)))
                        .subtract(p.getPrice().add(p.getPrice().multiply(vat).divide(new BigDecimal(100)))
                                .multiply(discount).divide(new BigDecimal(100))))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum;
    }
}
