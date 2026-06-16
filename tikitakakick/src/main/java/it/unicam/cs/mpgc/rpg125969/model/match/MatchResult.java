package it.unicam.cs.mpgc.rpg125969.model.match;

/**
 * Rappresenta il risultato finale di una partita.
 * Contiene i gol segnati dalla squadra di casa e da quella ospite.
 */
public class MatchResult {

    private final int homeGoals;
    private final int awayGoals;

    /**
     * Costruisce un nuovo risultato di partita.
     *
     * @param homeGoals gol segnati dalla squadra di casa
     * @param awayGoals gol segnati dalla squadra ospite
     */
    public MatchResult(int homeGoals, int awayGoals) {
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public int getHomeGoals() { return homeGoals; }
    public int getAwayGoals() { return awayGoals; }

    /**
     * Restituisce true se la squadra di casa ha vinto.
     *
     * @return true se homeGoals > awayGoals
     */
    public boolean isHomeWin() { return homeGoals > awayGoals; }

    /**
     * Restituisce true se la partita è terminata in pareggio.
     *
     * @return true se homeGoals == awayGoals
     */
    public boolean isDraw() { return homeGoals == awayGoals; }

    @Override
    public String toString() {
        return homeGoals + " - " + awayGoals;
    }
}