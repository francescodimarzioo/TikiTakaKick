package it.unicam.cs.mpgc.rpg125969.view;

import it.unicam.cs.mpgc.rpg125969.controller.MatchController;
import it.unicam.cs.mpgc.rpg125969.controller.LeagueController;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchResult;
import it.unicam.cs.mpgc.rpg125969.model.match.TacticalChoice;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchEvent;
import it.unicam.cs.mpgc.rpg125969.util.GameContext;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

/**
 * Schermata della partita a turni di TikiTakaKick.
 * Permette al manager di scegliere la tattica per ogni turno.
 */
public class MatchView {

    private final VBox root;
    private final MatchController matchController;
    private final LeagueController leagueController;
    private int currentTurn = 1;
    private static final int TOTAL_TURNS = 3;

    private Label scoreLabel;
    private Label turnLabel;
    private VBox eventLog;

    /**
     * Costruisce la schermata della partita.
     */
    public MatchView() {
        this.matchController = new MatchController();
        this.leagueController = new LeagueController(GameContext.getInstance().getLeague());

        matchController.startMatch(
                GameContext.getInstance().getPlayerTeam(),
                GameContext.getInstance().getLeague().getTeams().get(1)
        );

        root = new VBox(0);
        root.setStyle("-fx-background-color: #081c15;");

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(25, 40, 25, 40));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1b4332;");

        Label title = new Label("⚽  Partita in corso");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        header.getChildren().add(title);

        // Scoreboard
        HBox scoreboard = new HBox(20);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.setPadding(new Insets(30));
        scoreboard.setStyle("-fx-background-color: #0d2b1e;");

        Label homeLabel = new Label(GameContext.getInstance().getPlayerTeam().getName());
        homeLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        scoreLabel = new Label("0 — 0");
        scoreLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #52b788;");

        Label awayLabel = new Label(GameContext.getInstance().getLeague().getTeams().get(1).getName());
        awayLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        turnLabel = new Label("Turno 1 / 3  (0' - 30')");
        turnLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #95d5b2;");

        VBox scoreBox = new VBox(8, scoreLabel, turnLabel);
        scoreBox.setAlignment(Pos.CENTER);

        scoreboard.getChildren().addAll(homeLabel, scoreBox, awayLabel);

        // Log eventi
        eventLog = new VBox(6);
        eventLog.setPadding(new Insets(20, 40, 20, 40));

        ScrollPane scroll = new ScrollPane(eventLog);
        scroll.setStyle("-fx-background: #081c15; -fx-background-color: #081c15;");
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        // Scelte tattiche
        HBox choices = new HBox(16);
        choices.setAlignment(Pos.CENTER);
        choices.setPadding(new Insets(20, 40, 20, 40));
        choices.setStyle("-fx-background-color: #0d2b1e;");

        Button btnAttack   = createChoiceButton("⚔️  Attacco",   "Alto rischio, alto rendimento", TacticalChoice.ATTACK, choices);
        Button btnBalanced = createChoiceButton("⚖️  Bilanciato", "Equilibrio tra attacco e difesa", TacticalChoice.BALANCED, choices);
        Button btnDefend   = createChoiceButton("🛡  Difesa",    "Proteggi il risultato",          TacticalChoice.DEFEND, choices);

        choices.getChildren().addAll(btnAttack, btnBalanced, btnDefend);

        root.getChildren().addAll(header, scoreboard, scroll, choices);
    }

    /**
     * Crea un bottone per la scelta tattica.
     *
     * @param label       etichetta del bottone
     * @param description descrizione della tattica
     * @param choice      la scelta tattica associata
     * @param choices     il pannello contenitore
     * @return il bottone stilizzato
     */
    private Button createChoiceButton(String label, String description,
                                      TacticalChoice choice, HBox choices) {
        Button btn = new Button(label + "\n" + description);
        btn.setStyle(
                "-fx-background-color: #1b4332; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 13px; -fx-padding: 14 24; " +
                        "-fx-cursor: hand; -fx-background-radius: 8; " +
                        "-fx-border-color: #2d6a4f; -fx-border-radius: 8; -fx-border-width: 1;"
        );
        btn.setPrefWidth(240);
        btn.setOnAction(e -> playTurn(choice, choices));
        return btn;
    }

    /**
     * Esegue un turno di partita con la scelta tattica fornita.
     *
     * @param choice  la scelta tattica del manager
     * @param choices il pannello dei bottoni da disabilitare tra un turno e l'altro
     */
    private void playTurn(TacticalChoice choice, HBox choices) {
        matchController.playTurn(choice, currentTurn);

        // Aggiorna il log eventi
        for (MatchEvent event : matchController.getCurrentMatch().getEvents()) {
            if (eventLog.getChildren().size() < currentTurn) {
                Label eventLabel = new Label("⚡ " + event.toString());
                eventLabel.setStyle("-fx-text-fill: " +
                        (event.isGoal() ? "#52b788" : "#95d5b2") +
                        "; -fx-font-size: 14px;");
                eventLog.getChildren().add(eventLabel);
            }
        }

        // Aggiorna il punteggio
        MatchResult result = matchController.getResult();
        scoreLabel.setText(result.getHomeGoals() + " — " + result.getAwayGoals());

        currentTurn++;

        if (currentTurn <= TOTAL_TURNS) {
            int start = (currentTurn - 1) * 30;
            int end = currentTurn * 30;
            turnLabel.setText("Turno " + currentTurn + " / 3  (" + start + "' - " + end + "')");
        } else {
            // Partita finita
            choices.getChildren().clear();
            leagueController.updateStandings(
                    GameContext.getInstance().getPlayerTeam(),
                    GameContext.getInstance().getLeague().getTeams().get(1),
                    result
            );
            leagueController.nextMatchday();

            Label endLabel = new Label("🏁 Partita terminata! " +
                    GameContext.getInstance().getPlayerTeam().getName() + " " +
                    result.getHomeGoals() + " - " + result.getAwayGoals() + " " +
                    GameContext.getInstance().getLeague().getTeams().get(1).getName()
            );
            endLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");

            Button btnMenu = new Button("← Torna al Menu");
            btnMenu.setStyle(
                    "-fx-background-color: #2d6a4f; -fx-text-fill: #ffffff; " +
                            "-fx-font-size: 13px; -fx-padding: 8 20; " +
                            "-fx-cursor: hand; -fx-background-radius: 6;"
            );
            btnMenu.setOnAction(e -> MainApp.showMainMenu());

            VBox endBox = new VBox(12, endLabel, btnMenu);
            endBox.setAlignment(Pos.CENTER);
            choices.getChildren().add(endBox);
        }
    }

    /**
     * Restituisce il nodo radice della schermata.
     *
     * @return il VBox principale della schermata
     */
    public VBox getView() { return root; }
}