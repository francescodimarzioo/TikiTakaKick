package it.unicam.cs.mpgc.rpg125969.util;

import it.unicam.cs.mpgc.rpg125969.model.league.League;
import it.unicam.cs.mpgc.rpg125969.model.league.Matchday;
import it.unicam.cs.mpgc.rpg125969.model.match.Match;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;

import java.util.List;

/**
 * Singleton che mantiene lo stato globale del gioco.
 * Contiene il riferimento alla squadra del giocatore e al campionato.
 * La creazione delle squadre è delegata a TeamFactory (SRP).
 */
public class GameContext {

    private static GameContext instance;
    private final TeamFactory teamFactory;
    private Team playerTeam;
    private League league;

    /**
     * Costruttore privato — inizializza il gioco con squadra di default.
     */
    private GameContext() {
        this.teamFactory = new TeamFactory();
        initializeWithTeam("Juventus");
    }

    /**
     * Restituisce l'istanza unica di GameContext.
     *
     * @return l'istanza singleton
     */
    public static GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }

    /**
     * Inizializza il campionato con la squadra scelta dal giocatore.
     * Delega la creazione delle squadre a TeamFactory.
     *
     * @param chosenTeamName il nome della squadra scelta
     */
    public void initializeWithTeam(String chosenTeamName) {
        List<Team> allTeams = teamFactory.createAllTeams();

        playerTeam = allTeams.stream()
                .filter(t -> t.getName().equals(chosenTeamName))
                .findFirst()
                .orElse(allTeams.get(0));

        league = new League("Serie A");
        for (Team team : allTeams) {
            league.addTeam(team);
        }

        List<Team> opponents = allTeams.stream()
                .filter(t -> !t.equals(playerTeam))
                .toList();

        for (int i = 0; i < opponents.size(); i++) {
            Matchday matchday = new Matchday(i + 1);
            matchday.addMatch(new Match(playerTeam, opponents.get(i)));
            league.addMatchday(matchday);
        }
    }

    public Team getPlayerTeam() { return playerTeam; }
    public League getLeague() { return league; }

    /**
     * Resetta il gioco ricreando lo stato iniziale.
     */
    public void reset() {
        instance = null;
    }
}