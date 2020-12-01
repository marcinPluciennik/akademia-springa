package com.springcourse.homework8notebookhibernate.gui;

import com.springcourse.homework8notebookhibernate.controller.NoteRestController;
import com.springcourse.homework8notebookhibernate.model.Note;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route("add-note")
public class AddNoteGui extends VerticalLayout {

    private NoteRestController noteRestController;

    @Autowired
    public AddNoteGui(NoteRestController noteRestController) {
        this.noteRestController = noteRestController;

        Label label = new Label();
        label.add("ADD NEW NOTE TO NOTEBOOK:");

        TextArea textArea = new TextArea("Content");
        textArea.getStyle().set("minHeight", "150px");
        textArea.setPlaceholder("Write here ...");

        Button buttonAdd = new Button("Add Note");
        Dialog dialog = new Dialog();

        buttonAdd.addClickListener(clickEvent -> {
            Note note = new Note(LocalDate.now(), textArea.getValue());
            noteRestController.addNote(note);
            dialog.add(new Text("Success! New Note has been added to notebook!"));
            dialog.open();
        });
        add(label, textArea, buttonAdd);
    }
}
