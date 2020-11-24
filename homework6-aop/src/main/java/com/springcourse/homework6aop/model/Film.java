package com.springcourse.homework6aop.model;

public class Film {
    private String title;
    private int year;

    public Film(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
