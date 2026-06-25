package it.unicam.cs.mpgc.rpg125969.view;

import it.unicam.cs.mpgc.rpg125969.controller.LeagueController;
import it.unicam.cs.mpgc.rpg125969.controller.MatchController;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchResult;
import it.unicam.cs.mpgc.rpg125969.model.match.TacticalChoice;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import it.unicam.cs.mpgc.rpg125969.util.GameContext;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.List;
import java.util.Random;

/**
 * Schermata della partita con timer automatico ed eventi casuali.
 * Il manager può cambiare strategia una volta per tempo.
 */
public class MatchView {

    private final VBox root;
    private final MatchController matchController;
    private final LeagueController leagueController;
    private final Random random = new Random();

    private Label scoreLabel;
    private Label minuteLabel;
    private Label turnLabel;
    private VBox eventLog;
    private ProgressBar progressBar;
    private Timeline timeline;

    private int currentMinute = 0;
    private TacticalChoice currentChoice = TacticalChoice.BALANCED;
    private boolean askedFirstHalf = false;
    private boolean askedSecondHalf = false;
    private int homeGoals = 0;
    private int awayGoals = 0;

    /** Eventi casuali non legati ai gol */
    private final List<String> randomEvents = List.of(
            "Calcio d'angolo per %s",
            "Fallo fischiato contro %s",
            "Bella parata del portiere di %s",
            "Tiro fuori di poco di %s",
            "Giallo per un giocatore di %s",
            "Occasione sprecata da %s",
            "Gran recupero difensivo di %s",
            "Traversa! Che occasione per %s",
            "Fuorigioco annullato a %s",
            "Rimessa laterale per %s"
    );

    /**
     * Costruisce la schermata della partita.
     */
    public MatchView() {
        this.matchController = new MatchController();
        this.leagueController = new LeagueController(GameContext.getInstance().getLeague());

        String playerName = GameContext.getInstance().getPlayerTeam().getName();

        // Prende l'avversario della giornata corrente
        Team opponent = GameContext.getInstance().getLeague().getMatchdays()
                .get(GameContext.getInstance().getLeague().getCurrentMatchday() - 1)
                .getMatches().get(0).getAway();
        String opponentName = opponent.getName();

        matchController.startMatch(
                GameContext.getInstance().getPlayerTeam(),
                opponent
        );

        root = new VBox(0);
        root.setStyle("-fx-background-color: #081c15;");

        // Header
        HBox header = new HBox();
        header.setPadding(new Insets(20, 40, 20, 40));
        header.setAlignment(Pos.CENTER_LEFT);
        header.setStyle("-fx-background-color: #1b4332;");
        Label title = new Label("⚽  Partita in corso — Serie A");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");
        Label matchdayLabel = new Label("Giornata " + GameContext.getInstance().getLeague().getCurrentMatchday());
        matchdayLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #95d5b2;");
        HBox headerSpacer = new HBox();
        HBox.setHgrow(headerSpacer, Priority.ALWAYS);
        header.getChildren().addAll(title, headerSpacer, matchdayLabel);

        // Scoreboard
        HBox scoreboard = new HBox(40);
        scoreboard.setAlignment(Pos.CENTER);
        scoreboard.setPadding(new Insets(25, 40, 15, 40));
        scoreboard.setStyle("-fx-background-color: #0d2b1e;");

        Label homeLabel = new Label(playerName);
        homeLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        scoreLabel = new Label("0 — 0");
        scoreLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-text-fill: #52b788;");

        Label awayLabel = new Label(opponentName);
        awayLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff;");

        minuteLabel = new Label("0'");
        minuteLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #52b788; -fx-font-weight: bold;");

        turnLabel = new Label("Primo Tempo");
        turnLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #95d5b2;");

        VBox scoreBox = new VBox(6, scoreLabel, minuteLabel, turnLabel);
        scoreBox.setAlignment(Pos.CENTER);
        scoreboard.getChildren().addAll(homeLabel, scoreBox, awayLabel);

        // Progress bar
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(900);
        progressBar.setPrefHeight(6);
        progressBar.setStyle("-fx-accent: #52b788;");

        // Log eventi
        eventLog = new VBox(8);
        eventLog.setPadding(new Insets(20, 40, 20, 40));
        Label waitLabel = new Label("⏳ La partita sta per iniziare...");
        waitLabel.setStyle("-fx-text-fill: #95d5b2; -fx-font-size: 14px;");
        eventLog.getChildren().add(waitLabel);

        ScrollPane scroll = new ScrollPane(eventLog);
        scroll.setStyle("-fx-background: #081c15; -fx-background-color: #081c15;");
        scroll.setFitToWidth(true);
        VBox.setVgrow(scroll, Priority.ALWAYS);

        root.getChildren().addAll(header, scoreboard, progressBar, scroll);

        startMatch(playerName, opponentName, opponent);
    }

    /**
     * Avvia il timer automatico della partita.
     *
     * @param playerName   nome della squadra del giocatore
     * @param opponentName nome della squadra avversaria
     * @param opponent     oggetto Team avversario
     */
    private void startMatch(String playerName, String opponentName, Team opponent) {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(333), e -> {
            if (currentMinute >= 90) return;

            currentMinute++;
            minuteLabel.setText(currentMinute + "'");
            progressBar.setProgress((double) currentMinute / 90);

            if (currentMinute <= 45) {
                turnLabel.setText("Primo Tempo");
            } else {
                turnLabel.setText("Secondo Tempo");
            }

            // Evento casuale ogni 5 minuti
            if (currentMinute % 5 == 0) {
                generateRandomEvent(playerName, opponentName);
            }

            // Tentativo di gol ogni 15 minuti
            if (currentMinute % 15 == 0) {
                generateGoalAttempt(playerName, opponentName, opponent);
            }

            // Cambio strategia a metà primo tempo
            if (currentMinute == 22 && !askedFirstHalf) {
                askedFirstHalf = true;
                timeline.pause();
                showTacticsDialog();
            }

            // Cambio strategia a metà secondo tempo
            if (currentMinute == 65 && !askedSecondHalf) {
                askedSecondHalf = true;
                timeline.pause();
                showTacticsDialog();
            }

            // Fine partita
            if (currentMinute >= 90) {
                timeline.stop();
                endMatch(playerName, opponentName, opponent);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    /**
     * Genera un evento casuale non legato ai gol.
     *
     * @param playerName   nome squadra giocatore
     * @param opponentName nome squadra avversaria
     */
    private void generateRandomEvent(String playerName, String opponentName) {
        String team = random.nextBoolean() ? playerName : opponentName;
        String template = randomEvents.get(random.nextInt(randomEvents.size()));
        String description = String.format(template, team);
        addEventToLog(currentMinute + "' — " + description, false);
    }

    /**
     * Genera un tentativo di gol basato sulla strategia attuale.
     *
     * @param playerName   nome squadra giocatore
     * @param opponentName nome squadra avversaria
     * @param opponent     oggetto Team avversario
     */
    private void generateGoalAttempt(String playerName, String opponentName, Team opponent) {
        int homeStrength = GameContext.getInstance().getPlayerTeam().getOverallStrength();
        int awayStrength = opponent.getOverallStrength();

        double attackMult = switch (currentChoice) {
            case ATTACK -> 1.4;
            case DEFEND -> 0.7;
            case BALANCED -> 1.0;
        };

        int homeChance = (int) (homeStrength * attackMult);
        int awayChance = awayStrength;

        int roll = random.nextInt(200);
        if (roll < homeChance / 3) {
            homeGoals++;
            scoreLabel.setText(homeGoals + " — " + awayGoals);
            addEventToLog("⚽ " + currentMinute + "' — GOL! " + playerName + " segna!", true);
        } else if (roll < homeChance / 3 + awayChance / 3) {
            awayGoals++;
            scoreLabel.setText(homeGoals + " — " + awayGoals);
            addEventToLog("⚽ " + currentMinute + "' — GOL! " + opponentName + " segna!", true);
        }
    }

    /**
     * Mostra il pannello per il cambio di strategia nella view.
     */
    private void showTacticsDialog() {
        HBox tacticsBar = new HBox(12);
        tacticsBar.setAlignment(Pos.CENTER);
        tacticsBar.setPadding(new Insets(15, 40, 15, 40));
        tacticsBar.setStyle("-fx-background-color: #2d6a4f;");

        Label label = new Label("💡 Cambio tattico! Scegli:");
        label.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px; -fx-font-weight: bold;");

        Button btnAttack   = new Button("⚔️ Attacco");
        Button btnBalanced = new Button("⚖️ Bilanciato");
        Button btnDefend   = new Button("🛡 Difesa");

        styleTacticsButton(btnAttack);
        styleTacticsButton(btnBalanced);
        styleTacticsButton(btnDefend);

        btnAttack.setOnAction(e -> {
            currentChoice = TacticalChoice.ATTACK;
            addEventToLog("📋 Tattica cambiata: Attacco", false);
            root.getChildren().remove(tacticsBar);
            timeline.play();
        });
        btnBalanced.setOnAction(e -> {
            currentChoice = TacticalChoice.BALANCED;
            addEventToLog("📋 Tattica cambiata: Bilanciato", false);
            root.getChildren().remove(tacticsBar);
            timeline.play();
        });
        btnDefend.setOnAction(e -> {
            currentChoice = TacticalChoice.DEFEND;
            addEventToLog("📋 Tattica cambiata: Difesa", false);
            root.getChildren().remove(tacticsBar);
            timeline.play();
        });

        tacticsBar.getChildren().addAll(label, btnAttack, btnBalanced, btnDefend);
        root.getChildren().add(2, tacticsBar);
    }

    /**
     * Applica lo stile ai bottoni del cambio tattica.
     *
     * @param button il bottone da stilizzare
     */
    private void styleTacticsButton(Button button) {
        button.setStyle(
                "-fx-background-color: #1b4332; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 13px; -fx-padding: 8 16; " +
                        "-fx-cursor: hand; -fx-background-radius: 6; " +
                        "-fx-border-color: #52b788; -fx-border-radius: 6; -fx-border-width: 1;"
        );
    }

    /**
     * Aggiunge un evento al log visivo della partita.
     *
     * @param text   testo dell'evento
     * @param isGoal true se è un gol
     */
    private void addEventToLog(String text, boolean isGoal) {
        Label eventLabel = new Label(text);
        eventLabel.setStyle("-fx-text-fill: " +
                (isGoal ? "#52b788" : "#95d5b2") +
                "; -fx-font-size: 14px;");
        eventLog.getChildren().add(eventLabel);
    }

    /**
     * Gestisce la fine della partita aggiornando classifica e giornata.
     *
     * @param playerName   nome squadra giocatore
     * @param opponentName nome squadra avversaria
     * @param opponent     oggetto Team avversario
     */
    private void endMatch(String playerName, String opponentName, Team opponent) {
        MatchResult result = new MatchResult(homeGoals, awayGoals);

        leagueController.updateStandings(
                GameContext.getInstance().getPlayerTeam(),
                opponent,
                result
        );
        leagueController.nextMatchday();

        String resultText = result.isHomeWin() ? "🏆 Vittoria!" :
                result.isDraw()    ? "🤝 Pareggio!" : "😔 Sconfitta";

        Label endLabel = new Label("🏁 Fischio finale!  " +
                playerName + "  " + homeGoals + " - " + awayGoals + "  " + opponentName);
        endLabel.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 16px; -fx-font-weight: bold;");

        Label resultLabel = new Label(resultText);
        resultLabel.setStyle("-fx-text-fill: #52b788; -fx-font-size: 24px; -fx-font-weight: bold;");

        Button btnMenu = new Button("← Torna al Menu");
        btnMenu.setStyle(
                "-fx-background-color: #2d6a4f; -fx-text-fill: #ffffff; " +
                        "-fx-font-size: 14px; -fx-padding: 10 24; " +
                        "-fx-cursor: hand; -fx-background-radius: 6;"
        );
        btnMenu.setOnAction(e -> MainApp.showMainMenu());

        VBox endBox = new VBox(12, endLabel, resultLabel, btnMenu);
        endBox.setAlignment(Pos.CENTER);
        endBox.setPadding(new Insets(20));
        eventLog.getChildren().add(endBox);
    }

    /**
     * Restituisce il nodo radice della schermata.
     *
     * @return il VBox principale
     */
    public VBox getView() { return root; }
}