package com.springcourse.homework8notebookhibernate.gui;

import com.springcourse.homework8notebookhibernate.controller.NoteRestController;
import com.springcourse.homework8notebookhibernate.model.Note;
import com.springcourse.homework8notebookhibernate.service.NoteService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

@Route("edit-note")
public class EditNoteGui extends VerticalLayout {

    private NoteRestController noteRestController;
    private NoteService service;

    @Autowired
    public EditNoteGui(NoteRestController noteRestController, NoteService service) {
        this.noteRestController = noteRestController;
        this.service = service;

        Label label = new Label();
        label.add("EDIT NOTE:");

        TextField textFieldId = new TextField("Id");

        TextArea textArea = new TextArea("New content");
        textArea.getStyle().set("minHeight", "150px");
        textArea.setPlaceholder("Write here ...");

        Button buttonEdit = new Button("Edit note");
        Dialog dialog = new Dialog();

        buttonEdit.addClickListener(clickEvent -> {
            Note note = new Note(LocalDate.now(), textArea.getValue());
            try{
                noteRestController.editNote(service.convertToLong(textFieldId.getValue()), note);
                dialog.add(new Text("Note has been edited!"));
                dialog.open();
            }catch (Exception e){
                dialog.add(new Text("ERROR, there is no id like " + textFieldId.getValue() + "!"));
                dialog.open();
            }
        });
        add(label, textFieldId, textArea, buttonEdit);
    }
}
