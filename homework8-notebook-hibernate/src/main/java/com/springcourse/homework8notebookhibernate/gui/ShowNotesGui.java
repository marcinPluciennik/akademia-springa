package com.springcourse.homework8notebookhibernate.gui;

import com.springcourse.homework8notebookhibernate.controller.NoteRestController;
import com.springcourse.homework8notebookhibernate.model.Note;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("show-notes")
public class ShowNotesGui extends VerticalLayout {

    private NoteRestController noteRestController;

    @Autowired
    public ShowNotesGui(NoteRestController noteRestController) {
        this.noteRestController = noteRestController;

        Label label = new Label();
        label.add("NOTEBOOK:");

        Grid<Note> grid = new Grid<>(Note.class);
        grid.setItems(noteRestController.getNotes());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("id", "date", "content");

        add(label, grid);
    }
}
