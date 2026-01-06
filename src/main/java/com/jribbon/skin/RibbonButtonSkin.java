package com.jribbon.skin;

import com.jribbon.component.RibbonButton;
import com.jribbon.component.enums.RibbonButtonPriority;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class RibbonButtonSkin extends SkinBase<RibbonButton> {

    private final Pane layoutBox;
    private final Label label;
    private final Pane iconContainer;

    public RibbonButtonSkin(RibbonButton control) {
        super(control);

        label = new Label();
        label.textProperty().bind(control.textProperty());
        label.getStyleClass().add("ribbon-button-label");

        iconContainer = new HBox();
        iconContainer.getStyleClass().add("ribbon-button-icon");
        iconContainer.setMouseTransparent(true); // O clique deve ir para o botão, não pro ícone

        control.iconProperty().addListener((obs, oldVal, newVal) -> updateIcon(newVal));
        updateIcon(control.getIcon());

        // CORREÇÃO: Definimos o alinhamento ESPECÍFICO para cada tipo
        if (control.getPriority() == RibbonButtonPriority.TOP) {
            // Modo Grande (Top)
            VBox vbox = new VBox(2);
            vbox.setAlignment(Pos.CENTER); // VBox aceita setAlignment
            vbox.getStyleClass().add("ribbon-button-top");
            layoutBox = vbox;
        } else {
            // Modo Pequeno (Medium)
            HBox hbox = new HBox(5);
            hbox.setAlignment(Pos.CENTER_LEFT); // HBox aceita setAlignment
            hbox.getStyleClass().add("ribbon-button-medium");
            layoutBox = hbox;
        }

        layoutBox.getChildren().addAll(iconContainer, label);
        getChildren().add(layoutBox);

        // Tratamento de Clique
        control.setOnMouseClicked(e -> control.fire());
    }

    private void updateIcon(javafx.scene.Node icon) {
        iconContainer.getChildren().clear();
        if (icon != null) {
            iconContainer.getChildren().add(icon);
        }
    }
}