package com.jribbon.demo;

import com.jribbon.component.*;
import com.jribbon.component.enums.RibbonButtonPriority;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DemoLauncher extends Application {

    @Override
    public void start(Stage stage) {
        Ribbon ribbon = new Ribbon();

        // --- ABA INÍCIO ---
        RibbonTab home = new RibbonTab("Início");

        // GRUPO 1: Área de Transferência
        RibbonGroup clipboard = new RibbonGroup("Área de Transf.");

        // Botão 1: Colar (Grande)
        RibbonButton btnPaste = new RibbonButton("Colar", createIcon(Color.DODGERBLUE, 32), RibbonButtonPriority.TOP);
        btnPaste.setOnAction(e -> System.out.println("Ação: Colar"));

        // Botão 2 e 3: Copiar e Recortar (Pequenos)
        RibbonButton btnCopy = new RibbonButton("Copiar", createIcon(Color.GRAY, 16), RibbonButtonPriority.MEDIUM);
        RibbonButton btnCut = new RibbonButton("Recortar", createIcon(Color.GRAY, 16), RibbonButtonPriority.MEDIUM);

        // Coluna Organizadora (Empilha Copiar e Recortar)
        RibbonColumn editColumn = new RibbonColumn(btnCopy, btnCut);

        // --- MUDANÇA AQUI: Adicionando o Separador ---
        clipboard.getItems().addAll(
                btnPaste,
                new RibbonSeparator(), // <--- A LINHA MÁGICA
                editColumn );

        // Adiciona ao Grupo (Atenção: Adicione APENAS o btnPaste e a Coluna)
        // Se você adicionar o btnCopy aqui fora também, dará o erro de Duplicidade!
        //clipboard.getItems().addAll(btnPaste, editColumn);

        // GRUPO 2: Fonte
        RibbonGroup font = new RibbonGroup("Fonte");
        RibbonButton btnBold = new RibbonButton("Negrito", null, RibbonButtonPriority.MEDIUM);
        RibbonButton btnItalic = new RibbonButton("Itálico", null, RibbonButtonPriority.MEDIUM);

        font.getItems().addAll(btnBold, btnItalic);

        home.getGroups().addAll(clipboard, font);

        // --- ABA INSERIR ---
        RibbonTab insert = new RibbonTab("Inserir");
        RibbonGroup tablesGroup = new RibbonGroup("Tabelas");
        tablesGroup.getItems().add(new RibbonButton("Tabela", createIcon(Color.GREEN, 32), RibbonButtonPriority.TOP));
        insert.getGroups().add(tablesGroup);

        ribbon.getTabs().addAll(home, insert);

        // Layout Principal
        BorderPane root = new BorderPane();
        root.setTop(ribbon);
        root.setCenter(new TextArea("Área de edição..."));

        Scene scene = new Scene(root, 900, 600);
        stage.setScene(scene);
        stage.setTitle("J-Ribbon: Vertical Columns Fixed");
        stage.show();
    }

    // Método auxiliar para criar ícones falsos rapidinho
    private Rectangle createIcon(Color color, int size) {
        return new Rectangle(size, size, color);
    }

    public static void main(String[] args) {
        launch(args);
    }
}