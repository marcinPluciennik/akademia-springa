package com.springcourse.homework6aop.repository;

import com.springcourse.homework6aop.aspect.AfterAddFilm;
import com.springcourse.homework6aop.model.Film;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FilmRepo {

    private List<Film> filmList;

    public FilmRepo() {
        this.filmList = new ArrayList<>();
        filmList.add(new Film("Alien", 1989));
        filmList.add(new Film("Back to the future", 1985));
        filmList.add(new Film("Life", 2019));
        filmList.add(new Film("Gravity", 2005));
    }

    public List<Film> getFilmList() {
        return filmList;
    }

    @AfterAddFilm
    public boolean addFilm(Film film){
        return filmList.add(film);
    }

    @Override
    public String toString() {
        return "FilmRepo{" +
                "filmList=" + filmList +
                '}';
    }
}
