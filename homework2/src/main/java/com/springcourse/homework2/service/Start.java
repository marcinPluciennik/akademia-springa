package com.springcourse.homework2.service;

import com.springcourse.homework2.repository.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class Start {

    private Cart cart;

    @Autowired
    public Start(Cart cart) {
        this.cart = cart;
    }

    public void printCart(){
        System.out.println("Products in the cart:");
        cart.getProductList().forEach(System.out::println);
    }

    public void getSumOfProducts(){
        BigDecimal sum = cart.getProductList().stream()
                .map(p -> p.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total price: " + sum + " PLN");
    }
}
