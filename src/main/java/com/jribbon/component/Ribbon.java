package com.jribbon.component;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import com.jribbon.skin.RibbonSkin;

public class Ribbon extends Control {

    private final ObservableList<RibbonTab> tabs = FXCollections.observableArrayList();

    // NOVO: Estado de minimização
    private final BooleanProperty minimized = new SimpleBooleanProperty(this, "minimized", false);

    public Ribbon() {
        getStyleClass().add("ribbon");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonSkin(this);
    }

    public ObservableList<RibbonTab> getTabs() {
        return tabs;
    }

    // --- Boilerplate da Propriedade Minimized ---
    public BooleanProperty minimizedProperty() { return minimized; }
    public boolean isMinimized() { return minimized.get(); }
    public void setMinimized(boolean minimized) { this.minimized.set(minimized); }

    @Override
    public String getUserAgentStylesheet() {
        return getClass().getResource("/styles/jribbon.css").toExternalForm();
    }
}