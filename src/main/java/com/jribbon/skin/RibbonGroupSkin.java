package com.jribbon.skin;

import com.jribbon.component.RibbonGroup;
import javafx.collections.ListChangeListener;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class RibbonGroupSkin extends SkinBase<RibbonGroup> {

    private final HBox contentArea;

    public RibbonGroupSkin(RibbonGroup control) {
        super(control);

        VBox root = new VBox();
        root.getStyleClass().add("ribbon-group-root");

        // 1. Área de Itens (Botões)
        contentArea = new HBox();
        contentArea.getStyleClass().add("ribbon-group-content");
        VBox.setVgrow(contentArea, Priority.ALWAYS);// Ocupa todo espaço vertical disponível


        //-- CORREÇÃO AQUI: Carrega os itens que JÁ existem na lista ---
        contentArea.getChildren().addAll(control.getItems());


        // Sincroniza a lista lógica com a visual
        control.getItems().addListener((ListChangeListener<Node>) c -> {
            while (c.next()) {
                if (c.wasAdded()) {
                    contentArea.getChildren().addAll(c.getAddedSubList());
                }
                // TODO: Implementar wasRemoved se necessário
            }
        });

        // 2. Título do Grupo (Rodapé)
        Label titleLabel = new Label();
        titleLabel.textProperty().bind(control.titleProperty());
        titleLabel.getStyleClass().add("ribbon-group-title");
        titleLabel.setMaxWidth(Double.MAX_VALUE);

        // Separador para separar botões de outros grupos (Visual opcional)
        Separator separator = new Separator();
        separator.setOrientation(javafx.geometry.Orientation.VERTICAL);

        root.getChildren().addAll(contentArea, titleLabel);

        getChildren().add(root);
    }
}