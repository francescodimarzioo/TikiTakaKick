package it.unicam.cs.mpgc.rpg125969.model.match;

/**
 * Rappresenta un singolo evento accaduto durante una partita.
 * Un evento può essere un gol, una parata, un fallo, ecc.
 */
public class MatchEvent {

    private final int minute;
    private final String description;
    private final boolean isGoal;

    /**
     * Costruisce un nuovo evento di partita.
     *
     * @param minute      il minuto in cui si verifica l'evento
     * @param description la descrizione testuale dell'evento
     * @param isGoal      true se l'evento è un gol, false altrimenti
     */
    public MatchEvent(int minute, String description, boolean isGoal) {
        this.minute = minute;
        this.description = description;
        this.isGoal = isGoal;
    }

    public int getMinute() { return minute; }
    public String getDescription() { return description; }
    public boolean isGoal() { return isGoal; }

    @Override
    public String toString() {
        return minute + "' - " + description;
    }
}