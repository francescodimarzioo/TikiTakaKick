package it.unicam.cs.mpgc.rpg125969.model.league;

import it.unicam.cs.mpgc.rpg125969.model.match.Match;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta una giornata del campionato.
 * Ogni giornata contiene una lista di partite da disputare.
 */
public class Matchday {

    private final int number;
    private final List<Match> matches;
    private boolean completed;

    /**
     * Costruisce una nuova giornata di campionato.
     *
     * @param number il numero della giornata
     */
    public Matchday(int number) {
        this.number = number;
        this.matches = new ArrayList<>();
        this.completed = false;
    }

    /**
     * Aggiunge una partita alla giornata.
     *
     * @param match la partita da aggiungere
     */
    public void addMatch(Match match) {
        matches.add(match);
    }

    /**
     * Segna la giornata come completata.
     */
    public void complete() {
        this.completed = true;
    }

    public int getNumber() { return number; }
    public List<Match> getMatches() { return matches; }
    public boolean isCompleted() { return completed; }

    @Override
    public String toString() {
        return "Giornata " + number + (completed ? " (completata)" : " (da giocare)");
    }
}