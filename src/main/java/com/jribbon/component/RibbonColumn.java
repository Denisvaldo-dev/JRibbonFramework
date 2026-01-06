package com.jribbon.component;

import com.jribbon.skin.RibbonColumnSkin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Um container simples para empilhar botões pequenos verticalmente
 * dentro de um RibbonGroup.
 */
public class RibbonColumn extends Control {

    private final ObservableList<Node> items = FXCollections.observableArrayList();

    public RibbonColumn() {
        getStyleClass().add("ribbon-column");
    }

    // Construtor de conveniência para adicionar itens rápido
    public RibbonColumn(Node... nodes) {
        this();
        this.items.addAll(nodes);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonColumnSkin(this);
    }

    public ObservableList<Node> getItems() {
        return items;
    }
}