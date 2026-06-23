package it.unicam.cs.mpgc.rpg125969.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Schermata del menu principale di TikiTakaKick.
 * Stile stadium dashboard con palette verde campo da calcio.
 */
public class MainMenuView {

    private final HBox root;

    /**
     * Costruisce la schermata del menu principale.
     */
    public MainMenuView() {
        root = new HBox();
        root.setStyle("-fx-background-color: #1b4332;");

        // Pannello sinistro — branding
        VBox leftPanel = new VBox(12);
        leftPanel.setPrefWidth(420);
        leftPanel.setAlignment(Pos.CENTER);
        leftPanel.setStyle("-fx-background-color: #1b4332;");
        leftPanel.setPadding(new Insets(60));

        Label logo = new Label("⚽");
        logo.setStyle("-fx-font-size: 80px;");

        Label title = new Label("TikiTaka\nKick");
        title.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; " +
                "-fx-text-fill: #ffffff; -fx-text-alignment: center;");

        Label subtitle = new Label("Serie A Manager");
        subtitle.setStyle("-fx-font-size: 16px; -fx-text-fill: #95d5b2; " +
                "-fx-font-style: italic;");

        Label version = new Label("Stagione 2024/25");
        version.setStyle("-fx-font-size: 13px; -fx-text-fill: #52b788;");

        leftPanel.getChildren().addAll(logo, title, subtitle, version);

        // Separatore verticale
        Region separator = new Region();
        separator.setPrefWidth(3);
        separator.setStyle("-fx-background-color: #52b788;");

        // Pannello destro — menu
        VBox rightPanel = new VBox(16);
        rightPanel.setPrefWidth(480);
        rightPanel.setAlignment(Pos.CENTER_LEFT);
        rightPanel.setStyle("-fx-background-color: #081c15;");
        rightPanel.setPadding(new Insets(60));

        Label menuTitle = new Label("MENU PRINCIPALE");
        menuTitle.setStyle("-fx-font-size: 13px; -fx-text-fill: #52b788; " +
                "-fx-font-weight: bold; -fx-letter-spacing: 3;");

        Button btnRoster    = createMenuButton("👥  Gestisci Rosa",     "Visualizza e allena i tuoi giocatori");
        Button btnMatch     = createMenuButton("⚽  Gioca Partita",      "Affronta la prossima giornata");
        Button btnStandings = createMenuButton("🏆  Classifica",         "Visualizza la classifica di Serie A");
        Button btnExit      = createMenuButton("✕   Esci",              "Chiudi il gioco");

        btnRoster.setOnAction(e -> MainApp.showRosterView());
        btnMatch.setOnAction(e -> MainApp.showMatchView());
        btnStandings.setOnAction(e -> MainApp.showStandingsView());
        btnExit.setOnAction(e -> System.exit(0));

        rightPanel.getChildren().addAll(menuTitle, btnRoster, btnMatch, btnStandings, btnExit);
        root.getChildren().addAll(leftPanel, separator, rightPanel);
    }

    /**
     * Crea un bottone menu con titolo e sottotitolo descrittivo.
     *
     * @param label       testo principale del bottone
     * @param description descrizione secondaria
     * @return il bottone stilizzato
     */
    private Button createMenuButton(String label, String description) {
        Button btn = new Button(label + "\n" + description);
        btn.setStyle(
                "-fx-background-color: #1b4332; " +
                        "-fx-text-fill: #ffffff; " +
                        "-fx-font-size: 14px; " +
                        "-fx-alignment: CENTER_LEFT; " +
                        "-fx-padding: 16 24; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-border-color: #2d6a4f; " +
                        "-fx-border-radius: 8; " +
                        "-fx-border-width: 1;"
        );
        btn.setPrefWidth(360);
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #2d6a4f; " +
                        "-fx-text-fill: #ffffff; " +
                        "-fx-font-size: 14px; " +
                        "-fx-alignment: CENTER_LEFT; " +
                        "-fx-padding: 16 24; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-border-color: #52b788; " +
                        "-fx-border-radius: 8; " +
                        "-fx-border-width: 1;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: #1b4332; " +
                        "-fx-text-fill: #ffffff; " +
                        "-fx-font-size: 14px; " +
                        "-fx-alignment: CENTER_LEFT; " +
                        "-fx-padding: 16 24; " +
                        "-fx-cursor: hand; " +
                        "-fx-background-radius: 8; " +
                        "-fx-border-color: #2d6a4f; " +
                        "-fx-border-radius: 8; " +
                        "-fx-border-width: 1;"
        ));
        return btn;
    }

    /**
     * Restituisce il nodo radice della schermata.
     *
     * @return l'HBox principale della schermata
     */
    public HBox getView() { return root; }
}