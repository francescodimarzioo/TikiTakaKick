package it.unicam.cs.mpgc.rpg125969.view;

import it.unicam.cs.mpgc.rpg125969.model.league.TeamStanding;
import it.unicam.cs.mpgc.rpg125969.util.GameContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

/**
 * Schermata della classifica del campionato.
 * Stile dashboard moderna con palette verde campo da calcio.
 */
public class StandingsView {

    private final VBox root;

    /**
     * Costruisce la schermata della classifica.
     */
    public StandingsView() {
        root = new VBox(0);
        root.setStyle("-fx-background-color: #081c15;");

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(25, 40, 25, 40));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1b4332;");

        Label title = new Label("🏆  Classifica Serie A  —  Stagione 2024/25");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnBack = new Button("← Menu");
        styleButton(btnBack);
        btnBack.setOnAction(e -> MainApp.showMainMenu());

        header.getChildren().addAll(title, spacer, btnBack);

        // Tabella
        VBox table = new VBox(4);
        table.setPadding(new Insets(30, 40, 30, 40));

        // Intestazione colonne
        HBox colHeader = createRow("", "POS", "SQUADRA", "G", "V", "P", "S", "PT", true, false);
        table.getChildren().add(colHeader);

        int pos = 1;
        for (TeamStanding entry : GameContext.getInstance().getLeague().getLeagueTable().getSorted()) {
            boolean isPlayer = entry.getTeam().equals(GameContext.getInstance().getPlayerTeam());
            HBox row = createRow(
                    pos == 1 ? "🥇" : pos == 2 ? "🥈" : pos == 3 ? "🥉" : "",
                    String.valueOf(pos++),
                    entry.getTeam().getName(),
                    String.valueOf(entry.getPlayed()),
                    String.valueOf(entry.getWon()),
                    String.valueOf(entry.getDrawn()),
                    String.valueOf(entry.getLost()),
                    String.valueOf(entry.getPoints()),
                    false,
                    isPlayer
            );
            table.getChildren().add(row);
        }

        ScrollPane scroll = new ScrollPane(table);
        scroll.setStyle("-fx-background: #081c15; -fx-background-color: #081c15;");
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        root.getChildren().addAll(header, scroll);
    }

    /**
     * Crea una riga della tabella classifica.
     */
    private HBox createRow(String medal, String pos, String team, String g,
                           String v, String p, String s, String pt,
                           boolean isHeader, boolean isPlayerTeam) {
        HBox row = new HBox(0);
        row.setPadding(new Insets(12, 20, 12, 20));
        row.setAlignment(Pos.CENTER_LEFT);

        String bg = isHeader ? "#2d6a4f" : isPlayerTeam ? "#1b4332" : "#0d2b1e";
        String border = isPlayerTeam ? "#52b788" : "transparent";
        row.setStyle("-fx-background-color: " + bg + "; " +
                "-fx-background-radius: 6; " +
                "-fx-border-color: " + border + "; " +
                "-fx-border-radius: 6; " +
                "-fx-border-width: 1;");

        String color = isHeader ? "#95d5b2" : isPlayerTeam ? "#ffffff" : "#cccccc";
        String weight = isHeader ? "bold" : "normal";

        row.getChildren().addAll(
                styledLabel(medal, 30, color, weight),
                styledLabel(pos, 40, color, weight),
                styledLabel(team, 280, color, weight),
                styledLabel(g, 60, color, weight),
                styledLabel(v, 60, color, weight),
                styledLabel(p, 60, color, weight),
                styledLabel(s, 60, color, weight),
                styledLabel(pt, 60, "#52b788", "bold")
        );
        return row;
    }

    private Label styledLabel(String text, double width, String color, String weight) {
        Label label = new Label(text);
        label.setPrefWidth(width);
        label.setStyle("-fx-text-fill: " + color + "; -fx-font-weight: " + weight +
                "; -fx-font-size: 14px;");
        return label;
    }

    private void styleButton(Button button) {
        button.setStyle(
                "-fx-background-color: #2d6a4f; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 13px; -fx-padding: 8 20; " +
                        "-fx-cursor: hand; -fx-background-radius: 6;"
        );
    }

    public VBox getView() { return root; }
}