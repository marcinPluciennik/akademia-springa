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

    public Cart() {
        Product product1 = new Product("Book1", getRandomPrice());
        Product product2 = new Product("Book2", getRandomPrice());
        Product product3 = new Product("Book3", getRandomPrice());
        Product product4 = new Product("Book4", getRandomPrice());
        Product product5 = new Product("Book5", getRandomPrice());

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    public BigDecimal getRandomPrice(){
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(MIN_RANDOM_PRICE, MAX__RANDOM_PRICE));
    }

    public List<Product> getProductList() {
        return productList;
    }
}
