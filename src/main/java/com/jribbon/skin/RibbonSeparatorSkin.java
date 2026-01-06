package com.jribbon.skin;

import com.jribbon.component.RibbonSeparator;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.control.SkinBase;

public class RibbonSeparatorSkin extends SkinBase<RibbonSeparator> {

    public RibbonSeparatorSkin(RibbonSeparator control) {
        super(control);

        Separator sep = new Separator();
        sep.setOrientation(Orientation.VERTICAL);
        // O Separator nativo do JavaFX herda estilos. Vamos limpar e aplicar o nosso.
        sep.getStyleClass().add("ribbon-separator-line");

        getChildren().add(sep);
    }
}