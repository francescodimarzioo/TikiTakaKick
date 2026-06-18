package it.unicam.cs.mpgc.rpg125969.model.league;

import it.unicam.cs.mpgc.rpg125969.model.team.Team;

/**
 * Rappresenta la posizione in classifica di una singola squadra.
 * Tiene traccia di partite giocate, vinte, pareggiate, perse e punti.
 */
public class TeamStanding {

    private final Team team;
    private int played;
    private int won;
    private int drawn;
    private int lost;
    private int points;

    /**
     * Costruisce una nuova posizione in classifica per la squadra fornita.
     * Tutti i valori vengono inizializzati a zero.
     *
     * @param team la squadra associata a questa posizione
     */
    public TeamStanding(Team team) {
        this.team = team;
        this.played = 0;
        this.won = 0;
        this.drawn = 0;
        this.lost = 0;
        this.points = 0;
    }

    /**
     * Aggiorna la classifica in base al risultato della partita.
     *
     * @param isWin  true se la squadra ha vinto
     * @param isDraw true se la partita è terminata in pareggio
     */
    public void update(boolean isWin, boolean isDraw) {
        played++;
        if (isWin) {
            won++;
            points += 3;
        } else if (isDraw) {
            drawn++;
            points += 1;
        } else {
            lost++;
        }
    }

    public Team getTeam() { return team; }
    public int getPlayed() { return played; }
    public int getWon() { return won; }
    public int getDrawn() { return drawn; }
    public int getLost() { return lost; }
    public int getPoints() { return points; }

    @Override
    public String toString() {
        return team.getName() + " - Pt: " + points + " | G: " + played +
                " V: " + won + " P: " + drawn + " S: " + lost;
    }
}