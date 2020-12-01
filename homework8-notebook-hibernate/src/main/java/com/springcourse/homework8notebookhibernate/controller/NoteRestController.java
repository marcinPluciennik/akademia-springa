package com.springcourse.homework8notebookhibernate.controller;

import com.springcourse.homework8notebookhibernate.model.Note;
import com.springcourse.homework8notebookhibernate.repository.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteRestController {

    private NoteRepo noteRepo;

    @Autowired
    public NoteRestController(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    @GetMapping
    public List<Note> getNotes(){
        return noteRepo.findAll();

    }

    @PostMapping
    public void addNote(@RequestBody Note note){
        noteRepo.save(note);
    }

    @PutMapping
    public void editNote(@RequestParam Long id, @RequestBody Note newNote){
        noteRepo.deleteById(id);
        noteRepo.save(newNote);
    }
}
