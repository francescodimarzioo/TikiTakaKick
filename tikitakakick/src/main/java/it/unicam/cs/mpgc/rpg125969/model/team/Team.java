package it.unicam.cs.mpgc.rpg125969.model.team;

import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta una squadra di calcio in TikiTakaKick.
 * Contiene la rosa dei giocatori, il modulo tattico e il nome della squadra.
 */
public class Team {

    private final String name;
    private final List<Player> roster;
    private Formation formation;

    /**
     * Costruisce una nuova squadra con il nome e il modulo forniti.
     * La rosa viene inizializzata vuota.
     *
     * @param name      nome della squadra
     * @param formation modulo tattico iniziale
     */
    public Team(String name, Formation formation) {
        this.name = name;
        this.roster = new ArrayList<>();
        this.formation = formation;
    }

    /**
     * Aggiunge un giocatore alla rosa della squadra.
     *
     * @param player il giocatore da aggiungere
     */
    public void addPlayer(Player player) {
        roster.add(player);
    }

    /**
     * Rimuove un giocatore dalla rosa della squadra.
     *
     * @param player il giocatore da rimuovere
     */
    public void removePlayer(Player player) {
        roster.remove(player);
    }

    /**
     * Calcola la forza complessiva della squadra
     * come media delle statistiche di tutti i giocatori.
     *
     * @return la forza media della squadra (0-99)
     */
    public int getOverallStrength() {
        if (roster.isEmpty()) return 0;
        return roster.stream()
                .mapToInt(p -> (
                        p.getStatistics().getSpeed() +
                                p.getStatistics().getShooting() +
                                p.getStatistics().getPassing() +
                                p.getStatistics().getDefense() +
                                p.getStatistics().getStamina()) / 5)
                .sum() / roster.size();
    }

    public String getName() { return name; }
    public List<Player> getRoster() { return roster; }
    public Formation getFormation() { return formation; }
    public void setFormation(Formation formation) { this.formation = formation; }

    @Override
    public String toString() {
        return name + " [" + formation + "] - Forza: " + getOverallStrength();
    }
}
