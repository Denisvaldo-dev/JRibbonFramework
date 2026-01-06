package com.jribbon.skin;

import com.jribbon.component.Ribbon;
import com.jribbon.component.RibbonTab;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class RibbonSkin extends SkinBase<Ribbon> {

    private final VBox rootLayout;
    private final HBox header;
    private final StackPane body;
    private final ToggleGroup toggleGroup;

    // Componentes de Controle de Janela
    private final Region spacer;
    private final Button minimizeBtn;

    public RibbonSkin(Ribbon control) {
        super(control);

        rootLayout = new VBox();
        rootLayout.getStyleClass().add("ribbon-root");

        // 1. HEADER
        header = new HBox();
        header.getStyleClass().add("ribbon-header");

        // Inicializa Spacer e Botão (mas NÃO adiciona ao header ainda)
        spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        minimizeBtn = new Button();
        minimizeBtn.getStyleClass().add("ribbon-minimize-button");
        minimizeBtn.setOnAction(e -> control.setMinimized(!control.isMinimized()));

        control.minimizedProperty().addListener((obs, oldVal, isMin) -> updateMinimizeIcon(isMin));
        updateMinimizeIcon(control.isMinimized());

        // 2. BODY
        body = new StackPane();
        body.getStyleClass().add("ribbon-body");

        // Lógica de Colapso
        body.visibleProperty().bind(control.minimizedProperty().not());
        body.managedProperty().bind(control.minimizedProperty().not());

        rootLayout.getChildren().addAll(header, body);
        getChildren().add(rootLayout);

        toggleGroup = new ToggleGroup();

        // 3. CARGA INICIAL (Passado)
        // Adiciona abas existentes
        for (RibbonTab tab : control.getTabs()) {
            addTabVisual(tab);
        }

        // AGORA SIM, adicionamos os controles no final, garantindo que fiquem à direita
        header.getChildren().addAll(spacer, minimizeBtn);

        // 4. LISTENER PARA NOVAS ABAS (Futuro)
        control.getTabs().addListener((ListChangeListener<RibbonTab>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    // Truque: Removemos os controles da direita temporariamente
                    header.getChildren().removeAll(spacer, minimizeBtn);

                    // Adicionamos as novas abas (que entrarão no final da lista atual)
                    for (RibbonTab tab : change.getAddedSubList()) {
                        addTabVisual(tab);
                    }

                    // Colocamos os controles de volta na ponta direita
                    header.getChildren().addAll(spacer, minimizeBtn);
                }
            }
        });

        // Listener de Seleção
        toggleGroup.selectedToggleProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                RibbonTab selectedTab = (RibbonTab) newVal.getUserData();
                updateBodyContent(selectedTab);

                // Se o usuário clicou na aba, expande o ribbon se estiver minimizado
                if (control.isMinimized()) {
                    control.setMinimized(false);
                }
            }
        });
    }

    private void addTabVisual(RibbonTab tab) {
        ToggleButton btn = new ToggleButton(tab.getTitle());
        btn.getStyleClass().add("ribbon-tab-button");
        btn.setUserData(tab);
        btn.setToggleGroup(toggleGroup);

        // Duplo Clique para Minimizar
        btn.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Ribbon ribbon = getSkinnable();
                ribbon.setMinimized(!ribbon.isMinimized());
            }
        });

        // Simplesmente adiciona ao container. A ordem é controlada pelo construtor/listener.
        header.getChildren().add(btn);

        // Auto-seleção da primeira aba
        if (toggleGroup.getSelectedToggle() == null) {
            btn.setSelected(true);
            updateBodyContent(tab);
        }
    }

    private void updateBodyContent(RibbonTab tab) {
        body.getChildren().clear();
        if (tab != null) {
            HBox groupsContainer = new HBox();
            groupsContainer.setSpacing(5);
            groupsContainer.getChildren().addAll(tab.getGroups());
            body.getChildren().add(groupsContainer);
        }
    }

    private void updateMinimizeIcon(boolean isMinimized) {
        minimizeBtn.pseudoClassStateChanged(javafx.css.PseudoClass.getPseudoClass("minimized"), isMinimized);
    }
}