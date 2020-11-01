package com.springcourse.homework2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Cart {
    private List<Product> productList;
    private final int MIN_PRICE = 50;
    private final int MAX_PRICE = 300;

    public Cart() {
        Product product1 = new Product("Book1", getPrice());
        Product product2 = new Product("Book2", getPrice());
        Product product3 = new Product("Book3", getPrice());
        Product product4 = new Product("Book4", getPrice());
        Product product5 = new Product("Book5", getPrice());

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    public BigDecimal getPrice(){
        return BigDecimal.valueOf(ThreadLocalRandom.current().nextInt(MIN_PRICE, MAX_PRICE)).movePointLeft(2);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
