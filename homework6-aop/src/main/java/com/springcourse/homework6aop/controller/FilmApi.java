package com.springcourse.homework6aop.controller;

import com.springcourse.homework6aop.model.Film;
import com.springcourse.homework6aop.repository.FilmRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmApi {

    private FilmRepo filmRepo;

    @Autowired
    public FilmApi(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @GetMapping
    private ResponseEntity<List<Film>> getFilms(){
        return new ResponseEntity<>(filmRepo.getFilmList(), HttpStatus.OK);
    }

    @PostMapping
    private ResponseEntity addFilm(@RequestBody Film film){
        if (filmRepo.addFilm(film)){
            return new ResponseEntity(film, HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
