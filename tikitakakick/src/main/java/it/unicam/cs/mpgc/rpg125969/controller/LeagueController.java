package it.unicam.cs.mpgc.rpg125969.controller;

import it.unicam.cs.mpgc.rpg125969.model.league.League;
import it.unicam.cs.mpgc.rpg125969.model.league.Matchday;
import it.unicam.cs.mpgc.rpg125969.model.league.TeamStanding;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchResult;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;

import java.util.List;

/**
 * Controller responsabile della gestione del campionato.
 * Coordina l'avanzamento delle giornate e l'aggiornamento della classifica.
 */
public class LeagueController {

    private final League league;

    /**
     * Costruisce un nuovo LeagueController con il campionato fornito.
     *
     * @param league il campionato da gestire
     */
    public LeagueController(League league) {
        this.league = league;
    }

    /**
     * Aggiorna la classifica in base al risultato di una partita.
     *
     * @param home   la squadra di casa
     * @param away   la squadra ospite
     * @param result il risultato della partita
     */
    public void updateStandings(Team home, Team away, MatchResult result) {
        List<TeamStanding> standings = league.getLeagueTable().getSorted();

        standings.stream()
                .filter(s -> s.getTeam().equals(home))
                .findFirst()
                .ifPresent(s -> s.update(result.isHomeWin(), result.isDraw()));

        standings.stream()
                .filter(s -> s.getTeam().equals(away))
                .findFirst()
                .ifPresent(s -> s.update(!result.isHomeWin() && !result.isDraw(), result.isDraw()));
    }

    /**
     * Avanza alla giornata successiva del campionato.
     */
    public void nextMatchday() {
        league.nextMatchday();
    }

    /**
     * Restituisce la giornata corrente del campionato.
     *
     * @return la giornata corrente
     */
    public Matchday getCurrentMatchday() {
        int index = league.getCurrentMatchday() - 1;
        return league.getMatchdays().get(index);
    }

    /**
     * Restituisce la classifica ordinata per punti.
     *
     * @return lista ordinata delle posizioni in classifica
     */
    public List<TeamStanding> getStandings() {
        return league.getLeagueTable().getSorted();
    }

    /**
     * Restituisce il campionato gestito.
     *
     * @return il campionato
     */
    public League getLeague() {
        return league;
    }
}
