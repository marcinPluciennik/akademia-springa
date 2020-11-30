package com.springcourse.homework7newsdatabase.gui;

import com.springcourse.homework7newsdatabase.controller.ArticleRestController;
import com.springcourse.homework7newsdatabase.model.Article;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("show-articles")
public class ShowArticlesGui extends VerticalLayout {

    private ArticleRestController articleRestController;

    @Autowired
    public ShowArticlesGui(ArticleRestController articleRestController) {
        this.articleRestController = articleRestController;

        Details componentInfo = new Details("NEWS FROM THE USA:",
                new Text("Top stories:"));
        Grid<Article> grid = new Grid<>(Article.class);
        grid.setItems(articleRestController.getArticles());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);
        grid.setColumns("article_id", "author", "title", "description", "url", "urlToImage", "publishedAt", "content");

        add(componentInfo, grid);
    }
}
