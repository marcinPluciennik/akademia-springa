package com.springcourse.homework2.repository;

import com.springcourse.homework2.domain.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Repository
public class Cart {
    private List<Product> productList;
    private final int MIN_RANDOM_PRICE = 50;
    private final int MAX__RANDOM_PRICE = 300;
    private final int ELEMENT_QUANTITY = 5;

    public Cart() {
        productList = new ArrayList<>();

        for (int i = 1; i <= ELEMENT_QUANTITY; i++){
            productList.add(new Product("Book"+ i, getRandomPrice()));
        }
    }

    public BigDecimal getRandomPrice(){
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(MIN_RANDOM_PRICE, MAX__RANDOM_PRICE));
    }

    public List<Product> getProductList() {
        return productList;
    }
}
