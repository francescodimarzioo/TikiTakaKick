package it.unicam.cs.mpgc.rpg125969.view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Punto di ingresso dell'applicazione TikiTakaKick.
 * Inizializza la finestra principale e carica la schermata del menu.
 */
public class MainApp extends Application {

    private static Stage primaryStage;

    /**
     * Metodo principale di avvio dell'applicazione JavaFX.
     *
     * @param stage lo stage principale fornito da JavaFX
     */
    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("TikiTakaKick");
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        showMainMenu();
        primaryStage.show();
    }

    /**
     * Mostra la schermata del menu principale.
     */
    public static void showMainMenu() {
        MainMenuView menuView = new MainMenuView();
        Scene scene = new Scene(menuView.getView(), 900, 600);
        primaryStage.setScene(scene);
    }

    /**
     * Mostra la schermata di gestione della rosa e allenamento.
     */
    public static void showRosterView() {
        RosterView rosterView = new RosterView();
        Scene scene = new Scene(rosterView.getView(), 900, 600);
        primaryStage.setScene(scene);
    }

    /**
     * Mostra la schermata della classifica.
     */
    public static void showStandingsView() {
        StandingsView standingsView = new StandingsView();
        Scene scene = new Scene(standingsView.getView(), 900, 600);
        primaryStage.setScene(scene);
    }

    /**
     * Mostra la schermata della partita.
     */
    public static void showMatchView() {
        MatchView matchView = new MatchView();
        Scene scene = new Scene(matchView.getView(), 900, 600);
        primaryStage.setScene(scene);
    }

    /**
     * Punto di ingresso principale dell'applicazione.
     *
     * @param args argomenti della riga di comando
     */
    public static void main(String[] args) {
        launch(args);
    }
}
