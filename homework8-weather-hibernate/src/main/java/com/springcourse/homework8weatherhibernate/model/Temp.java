package com.springcourse.homework8weatherhibernate.model;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "temperatures")
public class Temp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time")
    private LocalTime localTime;

    @Column(name = "london_temp")
    private double temp;

    public Temp() {
    }

    public Temp(LocalTime localTime, double temp){
        this.localTime = localTime;
        this.temp = temp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
