package com.jribbon.component;

import com.jribbon.skin.RibbonGroupSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class RibbonGroup extends Control {

    private final StringProperty title = new SimpleStringProperty(this, "title", "Group");
    // O Grupo contém uma lista de Nodes (botões, checkboxes, etc)
    private final ObservableList<Node> items = FXCollections.observableArrayList();

    public RibbonGroup(String title) {
        setTitle(title);
        getStyleClass().add("ribbon-group");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonGroupSkin(this);
    }

    public ObservableList<Node> getItems() {
        return items;
    }

    // --- Properties ---
    public StringProperty titleProperty() { return title; }
    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
}