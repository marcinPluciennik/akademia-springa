package com.springcourse.homework8weatherhibernate.controller;

import com.springcourse.homework8weatherhibernate.model.Temp;
import com.springcourse.homework8weatherhibernate.repository.TempRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/temps")
public class TempRestController {

    private TempController tempController;
    private TempRepo tempRepo;

    @Autowired
    public TempRestController(TempController tempController, TempRepo tempRepo) {
        this.tempController = tempController;
        this.tempRepo = tempRepo;
    }

    @GetMapping
    public List<Temp> getTemps(){
        return tempRepo.findAll();
    }

    @Scheduled(fixedRate = 3600000)
    @PostMapping
    public void saveTemp(){
        tempRepo.save(tempController.getLondonTemp());
    }
}
