package com.springcourse.homework7newsdatabase.gui;

import com.springcourse.homework7newsdatabase.controller.ArticleRestController;
import com.springcourse.homework7newsdatabase.model.Article;
import com.springcourse.homework7newsdatabase.service.ArticleService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("edit-article")
public class EditArticleGui extends VerticalLayout {

    private ArticleRestController articleRestController;
    private ArticleService service;

    @Autowired
    public EditArticleGui(ArticleRestController articleRestController, ArticleService service) {
        this.articleRestController = articleRestController;
        this.service = service;

        Details componentInfo = new Details("EDIT ARTICLE",
                new Text("Fill the form below and press button:"));
        TextField textFieldId = new TextField("Article_id");
        TextField textFieldAuthor = new TextField("Author");
        TextField textFieldTitle = new TextField("Title");
        TextField textFieldDescription = new TextField("Description");
        TextField textFieldUrl = new TextField("Url");
        TextField textFieldUrlToImage = new TextField("Url To Image");
        TextField textFieldPublishedAt = new TextField("Published At");
        TextField textFieldContent = new TextField("Content");

        Button buttonAdd = new Button("Edit Article");
        Dialog dialog = new Dialog();

        buttonAdd.addClickListener(clickEvent -> {
            Article article = new Article(service.convertToLong(textFieldId.getValue()), textFieldAuthor.getValue(),
                    textFieldTitle.getValue(), textFieldDescription.getValue(), textFieldUrl.getValue(),
                    textFieldUrlToImage.getValue(), textFieldPublishedAt.getValue(), textFieldContent.getValue());
            boolean result = articleRestController.removeArticleById(article.getArticle_id());
            if (result){
                articleRestController.addArticle(article);
                dialog.add(new Text("The Article has been edited!"));
                dialog.open();
            }else{
                dialog.add(new Text("ERROR, there is no id like " + textFieldId.getValue() + "!"));
                dialog.open();
            }
        });

        add(componentInfo, textFieldId, textFieldAuthor, textFieldTitle, textFieldDescription, textFieldUrl,
                textFieldUrlToImage, textFieldPublishedAt, textFieldContent, buttonAdd);
    }
}
