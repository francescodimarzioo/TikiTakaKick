package it.unicam.cs.mpgc.rpg125969.model.league;

import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta il campionato di Serie A in TikiTakaKick.
 * Gestisce le squadre partecipanti, le giornate e la classifica.
 */
public class League {

    private final String name;
    private final List<Team> teams;
    private final List<Matchday> matchdays;
    private final LeagueTable leagueTable;
    private int currentMatchday;

    /**
     * Costruisce un nuovo campionato con il nome fornito.
     *
     * @param name il nome del campionato
     */
    public League(String name) {
        this.name = name;
        this.teams = new ArrayList<>();
        this.matchdays = new ArrayList<>();
        this.leagueTable = new LeagueTable();
        this.currentMatchday = 1;
    }

    /**
     * Aggiunge una squadra al campionato e la inserisce in classifica.
     *
     * @param team la squadra da aggiungere
     */
    public void addTeam(Team team) {
        teams.add(team);
        leagueTable.add(new TeamStanding(team));
    }

    /**
     * Aggiunge una giornata al calendario del campionato.
     *
     * @param matchday la giornata da aggiungere
     */
    public void addMatchday(Matchday matchday) {
        matchdays.add(matchday);
    }

    /**
     * Avanza alla giornata successiva del campionato.
     */
    public void nextMatchday() {
        if (currentMatchday < matchdays.size()) {
            currentMatchday++;
        }
    }

    public String getName() { return name; }
    public List<Team> getTeams() { return teams; }
    public List<Matchday> getMatchdays() { return matchdays; }
    public LeagueTable getStandings() { return leagueTable; }
    public int getCurrentMatchday() { return currentMatchday; }

    @Override
    public String toString() {
        return name + " - Giornata " + currentMatchday;
    }
}
