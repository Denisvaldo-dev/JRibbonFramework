📘 J-Ribbon Framework
Versão: 1.1.0.0 (Stable)
Requisitos: Java 21+ | JavaFX 21+
Arquitetura: Clean Architecture / Control-Skin Separation
1. Visão Geral
   O J-Ribbon é uma biblioteca de componentes de interface gráfica para JavaFX, desenhada para replicar a experiência de navegação "Ribbon" (faixas de opções) do Microsoft Office moderno.
   O framework utiliza Domain-Driven Design (DDD) para separar a lógica de estado da implementação visual, garantindo alta performance e facilidade de customização.
   ✨ Novidades da Versão 1.1
   Colapso/Expansão (Minimizar): O usuário pode ocultar a faixa de opções para ganhar espaço na tela.
   Interatividade Aprimorada: Suporte a duplo clique nas abas e botão de alternância dedicado.
   Estabilidade: Correção de bugs de renderização inicial e dependências do JavaFX Base.
2. Instalação (Maven)
   Como a biblioteca foi instalada no seu repositório local (.m2), adicione a seguinte dependência ao pom.xml de qualquer projeto que for utilizar o J-Ribbon:
   code
   Xml
   <dependencies>
   <dependency>
   <groupId>com.jribbon</groupId>
   <artifactId>core</artifactId>
   <version>1.1.0.0</version>
   </dependency>
   </dependencies>
3. Guia Rápido (Quick Start)
   Exemplo de como criar um Ribbon completo com abas, grupos, botões e separadores.
   code
   Java
   import com.jribbon.component.*;
   import com.jribbon.component.enums.RibbonButtonPriority;
   import javafx.application.Application;
   import javafx.scene.Scene;
   import javafx.scene.layout.BorderPane;
   import javafx.stage.Stage;

public class App extends Application {
@Override
public void start(Stage stage) {
// 1. Instancia o Container Principal
Ribbon ribbon = new Ribbon();

        // 2. Cria a Aba "Início"
        RibbonTab homeTab = new RibbonTab("Início");

        // 3. Cria um Grupo "Arquivo"
        RibbonGroup fileGroup = new RibbonGroup("Arquivo");

        // 4. Adiciona Botões (Grande e Pequeno)
        RibbonButton btnSave = new RibbonButton("Salvar", null, RibbonButtonPriority.TOP);
        RibbonButton btnExit = new RibbonButton("Sair", null, RibbonButtonPriority.MEDIUM);
        
        // 5. Adiciona um Separador visual
        fileGroup.getItems().addAll(btnSave, new RibbonSeparator(), btnExit);

        // 6. Monta a Hierarquia
        homeTab.getGroups().add(fileGroup);
        ribbon.getTabs().add(homeTab);

        // 7. Define no Layout (Topo)
        BorderPane root = new BorderPane();
        root.setTop(ribbon);

        stage.setScene(new Scene(root, 1024, 768));
        stage.show();
    }
}
4. Funcionalidades e API
   4.1. Minimizar Ribbon (Novo na v1.1)
   O framework agora suporta nativamente o estado "Minimizado", onde apenas os títulos das abas ficam visíveis.
   Interação do Usuário:
   Botão Canto Direito: Uma seta (chevron) no cabeçalho permite alternar o estado.
   Duplo Clique: Clicar duas vezes em qualquer aba alterna entre expandido/minimizado.
   Auto-Expansão: Se estiver minimizado e o usuário clicar (1x) em uma aba diferente, o Ribbon se abre automaticamente.
   Controle via Código:
   code
   Java
   // Verifica se está minimizado
   boolean isHidden = ribbon.isMinimized();

// Força a minimização via código
ribbon.setMinimized(true);

// Listener para reagir a mudanças
ribbon.minimizedProperty().addListener((obs, oldVal, newVal) -> {
System.out.println("Ribbon está minimizado? " + newVal);
});
4.2. Componentes Estruturais
Componente	Descrição	Método Principal
Ribbon	O container raiz.	getTabs().add(...)
RibbonTab	Uma aba (categoria).	getGroups().add(...)
RibbonGroup	Agrupamento de comandos.	getItems().add(...)
RibbonColumn	Container para empilhar itens verticalmente.	new RibbonColumn(node1, node2)
RibbonSeparator	Linha vertical divisória (Flat Design).	new RibbonSeparator()
4.3. Botões (RibbonButton)
Suporta dois tamanhos baseados na prioridade:
TOP: Ícone 32px (recomendado) acima do texto.
MEDIUM: Ícone 16px (recomendado) ao lado do texto.
code
Java
RibbonButton btn = new RibbonButton("Texto", iconeNode, RibbonButtonPriority.TOP);
btn.setOnAction(e -> System.out.println("Click!"));
5. Estilização e Temas (CSS)
   O J-Ribbon inclui um tema padrão (jribbon.css) estilo "Office Flat/Light". Você pode customizar a aparência sobrescrevendo as classes CSS no seu projeto.
   Classes Principais
   code
   CSS
   .ribbon { /* Container base */ }
   .ribbon-header { /* Barra de abas */ }
   .ribbon-body { /* Área de conteúdo (oculta quando minimizado) */ }

/* Botões das Abas */
.ribbon-tab-button:selected {
-fx-text-fill: #2b579a; /* Azul Destaque */
}

/* Botão de Minimizar (Novo v1.1) */
.ribbon-minimize-button {
-fx-background-color: transparent;
}
/* Ícone SVG da seta */
.ribbon-minimize-button { -fx-shape: "..."; }
6. Solução de Problemas Comuns
   Erro duplicare children added:
   Certifique-se de não adicionar o mesmo objeto RibbonButton em dois lugares diferentes. Crie instâncias novas para cada botão.
   Erro ClassNotFoundException ou NoClassDefFoundError:
   Se estiver criando um projeto novo sem módulos (module-info.java), não execute a classe que estende Application diretamente. Crie uma classe Starter com um public static void main que chama seu App.