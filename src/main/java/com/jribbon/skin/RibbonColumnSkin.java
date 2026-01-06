package com.jribbon.skin;

import com.jribbon.component.RibbonColumn;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.VBox;

public class RibbonColumnSkin extends SkinBase<RibbonColumn> {

    private final VBox container;

    public RibbonColumnSkin(RibbonColumn control) {
        super(control);

        container = new VBox(2); // Espaçamento de 2px entre botões
        container.getStyleClass().add("ribbon-column-container");

        // Carrega itens iniciais
        container.getChildren().addAll(control.getItems());

        // Listener para itens futuros
        control.getItems().addListener((ListChangeListener<Node>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    container.getChildren().addAll(c.getAddedSubList());
                }
            }
        });

        getChildren().add(container);
    }
}