package it.unicam.cs.mpgc.rpg125969.view;

import it.unicam.cs.mpgc.rpg125969.util.GameContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.util.List;

/**
 * Schermata di selezione della squadra all'avvio del gioco.
 * Il giocatore sceglie quale squadra di Serie A vuole gestire.
 */
public class TeamSelectionView {

    private final VBox root;

    /** Lista delle squadre disponibili. */
    private final List<String> teamNames = List.of(
            "Juventus", "Inter", "Milan", "Napoli", "Roma"
    );

    /**
     * Costruisce la schermata di selezione squadra.
     */
    public TeamSelectionView() {
        root = new VBox(0);
        root.setStyle("-fx-background-color: #081c15;");

        // Header
        VBox header = new VBox(8);
        header.setPadding(new Insets(30, 40, 25, 40));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #1b4332;");

        Label title = new Label("⚽  TikiTakaKick");
        title.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        Label subtitle = new Label("Scegli la squadra che vuoi allenare in Serie A");
        subtitle.setStyle("-fx-font-size: 15px; -fx-text-fill: #95d5b2;");

        Label season = new Label("Stagione 2025/26");
        season.setStyle("-fx-font-size: 13px; -fx-text-fill: #52b788; -fx-font-style: italic;");

        header.getChildren().addAll(title, subtitle, season);

        // Griglia squadre
        GridPane grid = new GridPane();
        grid.setHgap(16);
        grid.setVgap(16);
        grid.setPadding(new Insets(40));
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < teamNames.size(); i++) {
            String name = teamNames.get(i);
            Button btn = createTeamButton(name);
            grid.add(btn, i % 2, i / 2);
        }

        ScrollPane scroll = new ScrollPane(grid);
        scroll.setStyle("-fx-background: #081c15; -fx-background-color: #081c15;");
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        root.getChildren().addAll(header, scroll);
    }

    /**
     * Crea un bottone per la selezione di una squadra.
     *
     * @param teamName il nome della squadra
     * @return il bottone stilizzato
     */
    private Button createTeamButton(String teamName) {
        Button btn = new Button("🏟  " + teamName);
        btn.setPrefWidth(380);
        btn.setPrefHeight(70);
        btn.setStyle(
                "-fx-background-color: #0d2b1e; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 18px; -fx-font-weight: bold; " +
                        "-fx-cursor: hand; -fx-background-radius: 8; " +
                        "-fx-border-color: #2d6a4f; -fx-border-radius: 8; -fx-border-width: 1;"
        );
        btn.setOnMouseEntered(e -> btn.setStyle(
                "-fx-background-color: #2d6a4f; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 18px; -fx-font-weight: bold; " +
                        "-fx-cursor: hand; -fx-background-radius: 8; " +
                        "-fx-border-color: #52b788; -fx-border-radius: 8; -fx-border-width: 1;"
        ));
        btn.setOnMouseExited(e -> btn.setStyle(
                "-fx-background-color: #0d2b1e; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 18px; -fx-font-weight: bold; " +
                        "-fx-cursor: hand; -fx-background-radius: 8; " +
                        "-fx-border-color: #2d6a4f; -fx-border-radius: 8; -fx-border-width: 1;"
        ));
        btn.setOnAction(e -> selectTeam(teamName));
        return btn;
    }

    /**
     * Inizializza il gioco con la squadra selezionata e passa al menu principale.
     *
     * @param teamName il nome della squadra scelta dal giocatore
     */
    private void selectTeam(String teamName) {
        GameContext.getInstance().initializeWithTeam(teamName);
        MainApp.showMainMenu();
    }

    /**
     * Restituisce il nodo radice della schermata.
     *
     * @return il VBox principale
     */
    public VBox getView() { return root; }
}
