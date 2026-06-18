package it.unicam.cs.mpgc.rpg125969.model.league;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Rappresenta la classifica completa del campionato.
 * Le squadre vengono ordinate per punti in ordine decrescente.
 */
public class LeagueTable {

    private final List<TeamStanding> entries;

    /**
     * Costruisce una classifica vuota.
     */
    public LeagueTable() {
        this.entries = new ArrayList<>();
    }

    /**
     * Aggiunge una posizione in classifica.
     *
     * @param entry la posizione da aggiungere
     */
    public void add(TeamStanding entry) {
        entries.add(entry);
    }

    /**
     * Restituisce la classifica ordinata per punti decrescenti.
     *
     * @return lista ordinata delle posizioni in classifica
     */
    public List<TeamStanding> getSorted() {
        return entries.stream()
                .sorted(Comparator.comparingInt(TeamStanding::getPoints).reversed())
                .toList();
    }
}