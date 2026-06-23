package it.unicam.cs.mpgc.rpg125969.view;

import it.unicam.cs.mpgc.rpg125969.controller.TrainingController;
import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.training.TrainingType;
import it.unicam.cs.mpgc.rpg125969.util.GameContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Schermata di gestione della rosa e degli allenamenti.
 * Stile dashboard moderna con palette verde campo da calcio.
 */
public class RosterView {

    private final VBox root;
    private final TrainingController trainingController;

    /**
     * Costruisce la schermata di gestione della rosa.
     */
    public RosterView() {
        this.trainingController = new TrainingController();
        root = new VBox(0);
        root.setStyle("-fx-background-color: #081c15;");

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(25, 40, 25, 40));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1b4332;");

        Label title = new Label("👥  Rosa — " + GameContext.getInstance().getPlayerTeam().getName());
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button btnBack = new Button("← Menu");
        styleButton(btnBack);
        btnBack.setOnAction(e -> MainApp.showMainMenu());

        header.getChildren().addAll(title, spacer, btnBack);

        // Lista giocatori
        VBox playerList = new VBox(8);
        playerList.setPadding(new Insets(30, 40, 30, 40));

        for (Player player : GameContext.getInstance().getPlayerTeam().getRoster()) {
            playerList.getChildren().add(createPlayerCard(player));
        }

        ScrollPane scroll = new ScrollPane(playerList);
        scroll.setStyle("-fx-background: #081c15; -fx-background-color: #081c15;");
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        root.getChildren().addAll(header, scroll);
    }

    /**
     * Crea una card visiva per un giocatore.
     *
     * @param player il giocatore da visualizzare
     * @return il pannello HBox della card
     */
    private HBox createPlayerCard(Player player) {
        HBox card = new HBox(20);
        card.setPadding(new Insets(16, 20, 16, 20));
        card.setStyle("-fx-background-color: #0d2b1e; -fx-background-radius: 8; " +
                "-fx-border-color: #2d6a4f; -fx-border-radius: 8; -fx-border-width: 1;");
        card.setAlignment(Pos.CENTER_LEFT);

        // Ruolo badge
        Label roleBadge = new Label(getRoleEmoji(player));
        roleBadge.setStyle("-fx-font-size: 28px;");

        // Info giocatore
        VBox info = new VBox(4);
        Label name = new Label(player.getName());
        name.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        Label stats = new Label(
                "⚡ " + player.getStatistics().getSpeed() +
                        "   🎯 " + player.getStatistics().getShooting() +
                        "   🔄 " + player.getStatistics().getPassing() +
                        "   🛡 " + player.getStatistics().getDefense() +
                        "   💪 " + player.getStatistics().getStamina()
        );
        stats.setStyle("-fx-text-fill: #95d5b2; -fx-font-size: 13px;");

        Label state = new Label("● " + player.getState().toString());
        String stateColor = switch (player.getState()) {
            case FIT -> "#52b788";
            case TIRED -> "#f4a261";
            case INJURED -> "#e63946";
        };
        state.setStyle("-fx-text-fill: " + stateColor + "; -fx-font-size: 12px;");

        info.getChildren().addAll(name, stats, state);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Allenamento
        ComboBox<TrainingType> combo = new ComboBox<>();
        combo.getItems().addAll(TrainingType.values());
        combo.setValue(TrainingType.SPEED);
        combo.setStyle("-fx-background-color: #1b4332; -fx-text-fill: white;");

        Button btnTrain = new Button("Allena");
        styleButton(btnTrain);
        btnTrain.setOnAction(e -> {
            boolean ok = trainingController.train(player, combo.getValue());
            if (ok) {
                stats.setText(
                        "⚡ " + player.getStatistics().getSpeed() +
                                "   🎯 " + player.getStatistics().getShooting() +
                                "   🔄 " + player.getStatistics().getPassing() +
                                "   🛡 " + player.getStatistics().getDefense() +
                                "   💪 " + player.getStatistics().getStamina()
                );
                String sc = switch (player.getState()) {
                    case FIT -> "#52b788";
                    case TIRED -> "#f4a261";
                    case INJURED -> "#e63946";
                };
                state.setText("● " + player.getState().toString());
                state.setStyle("-fx-text-fill: " + sc + "; -fx-font-size: 12px;");
            } else {
                showAlert("Giocatore non disponibile", "Il giocatore è infortunato e non può allenarsi.");
            }
        });

        card.getChildren().addAll(roleBadge, info, spacer, combo, btnTrain);
        return card;
    }

    /**
     * Restituisce l'emoji corrispondente al ruolo del giocatore.
     *
     * @param player il giocatore
     * @return l'emoji del ruolo
     */
    private String getRoleEmoji(Player player) {
        return switch (player.getRole()) {
            case GOALKEEPER -> "🧤";
            case DEFENDER -> "🛡";
            case MIDFIELDER -> "🔄";
            case ATTACKER -> "⚽";
        };
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
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