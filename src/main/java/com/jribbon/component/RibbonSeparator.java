package com.jribbon.component;

import com.jribbon.skin.RibbonSeparatorSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Uma linha vertical para separar logicamente botões dentro de um Grupo.
 */
public class RibbonSeparator extends Control {

    public RibbonSeparator() {
        getStyleClass().add("ribbon-separator");
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonSeparatorSkin(this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return getClass().getResource("/styles/jribbon.css").toExternalForm();
    }
}