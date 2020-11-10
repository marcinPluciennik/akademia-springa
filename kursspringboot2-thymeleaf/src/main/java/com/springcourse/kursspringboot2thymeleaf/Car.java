package com.springcourse.kursspringboot2thymeleaf;

public class Car {
    private String mark;
    private String model;


    public Car(String mark, String model) {
        this.model = model;
        this.mark = mark;
    }

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
