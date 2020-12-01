package com.springcourse.homework8notebookhibernate.repository;

import com.springcourse.homework8notebookhibernate.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepo extends JpaRepository<Note, Long> {

}
