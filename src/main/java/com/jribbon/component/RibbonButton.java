package com.jribbon.component;

import com.jribbon.component.enums.RibbonButtonPriority;
import com.jribbon.skin.RibbonButtonSkin;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class RibbonButton extends Control {

    private final StringProperty text = new SimpleStringProperty(this, "text", "");
    private final ObjectProperty<Node> icon = new SimpleObjectProperty<>(this, "icon");
    private final ObjectProperty<RibbonButtonPriority> priority = new SimpleObjectProperty<>(this, "priority", RibbonButtonPriority.MEDIUM);

    // Propriedade para tratar o clique (Ação)
    private final ObjectProperty<EventHandler<ActionEvent>> onAction = new SimpleObjectProperty<>();

    public RibbonButton(String text) {
        setText(text);
        getStyleClass().add("ribbon-button");
    }

    public RibbonButton(String text, Node icon) {
        this(text);
        setIcon(icon);
    }

    public RibbonButton(String text, Node icon, RibbonButtonPriority priority) {
        this(text, icon);
        setPriority(priority);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new RibbonButtonSkin(this);
    }

    // Método para disparar a ação
    public void fire() {
        if (getOnAction() != null) {
            getOnAction().handle(new ActionEvent(this, null));
        }
    }

    @Override
    public String getUserAgentStylesheet() {
        return getClass().getResource("/styles/jribbon.css").toExternalForm();
    }

    // --- Getters e Setters ---
    public StringProperty textProperty() { return text; }
    public String getText() { return text.get(); }
    public void setText(String text) { this.text.set(text); }

    public ObjectProperty<Node> iconProperty() { return icon; }
    public Node getIcon() { return icon.get(); }
    public void setIcon(Node icon) { this.icon.set(icon); }

    public ObjectProperty<RibbonButtonPriority> priorityProperty() { return priority; }
    public RibbonButtonPriority getPriority() { return priority.get(); }
    public void setPriority(RibbonButtonPriority priority) { this.priority.set(priority); }

    public ObjectProperty<EventHandler<ActionEvent>> onActionProperty() { return onAction; }
    public EventHandler<ActionEvent> getOnAction() { return onAction.get(); }
    public void setOnAction(EventHandler<ActionEvent> value) { onAction.set(value); }
}