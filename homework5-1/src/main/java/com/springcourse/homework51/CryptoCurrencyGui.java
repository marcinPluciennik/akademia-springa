package com.springcourse.homework51;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;


@Route
public class CryptoCurrencyGui extends VerticalLayout {

    private CryptoCurrencyController cryptoCurrencyController;

    @Autowired
    public CryptoCurrencyGui(CryptoCurrencyController cryptoCurrencyController) {
        this.cryptoCurrencyController = cryptoCurrencyController;

        Details component = new Details("CRYPTOCURRENCY LIST:",
                new Text("Buy cryptocurrency and be rich!"));

        Grid<CryptoCurrency> grid = new Grid<>();
        grid.setItems(Arrays.asList(cryptoCurrencyController.getCryptoCurrencies()));
        grid.addColumn(CryptoCurrency::getId).setHeader("ID");
        grid.addColumn(CryptoCurrency::getName).setHeader("NAME");
        grid.addColumn(CryptoCurrency::getSymbol).setHeader("SYMBOL");
        grid.addColumn(CryptoCurrency::getRank).setHeader("RANK");
        grid.addColumn(CryptoCurrency::getIsNew).setHeader("IS NEW");
        grid.addColumn(CryptoCurrency::getIsActive).setHeader("IS ACTIVE");
        grid.addColumn(CryptoCurrency::getType).setHeader("TYPE");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER,
                GridVariant.LUMO_NO_ROW_BORDERS, GridVariant.LUMO_ROW_STRIPES);

        add(component, grid);
    }
}
