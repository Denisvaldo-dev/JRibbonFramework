package com.jribbon.component;

import com.jribbon.skin.RibbonTabSkin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class RibbonTab extends Control {

    private final StringProperty title = new SimpleStringProperty(this, "title", "");

    // MUDANÇA: Agora a aba gerencia uma lista de grupos
    private final ObservableList<RibbonGroup> groups = FXCollections.observableArrayList();

    public RibbonTab(String title) {
        setTitle(title);
        getStyleClass().add("ribbon-tab");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonTabSkin(this);
    }

    public ObservableList<RibbonGroup> getGroups() {
        return groups;
    }

    public StringProperty titleProperty() { return title; }
    public String getTitle() { return title.get(); }
    public void setTitle(String title) { this.title.set(title); }
}