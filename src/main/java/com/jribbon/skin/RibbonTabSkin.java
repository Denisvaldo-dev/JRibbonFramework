package com.jribbon.skin;

import com.jribbon.component.RibbonTab;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;

public class RibbonTabSkin extends SkinBase<RibbonTab> {

    private final StackPane container;
    private final Label titleLabel;

    public RibbonTabSkin(RibbonTab control) {
        super(control);

        // Construção do Grafo de Cena Interno
        container = new StackPane();
        container.getStyleClass().add("tab-container");

        titleLabel = new Label();
        titleLabel.textProperty().bind(control.titleProperty());
        titleLabel.getStyleClass().add("tab-label");

        container.getChildren().add(titleLabel);

        // Define este container como o visual do Control
        getChildren().add(container);
    }
}