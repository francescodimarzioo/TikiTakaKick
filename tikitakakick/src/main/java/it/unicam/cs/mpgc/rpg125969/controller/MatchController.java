package it.unicam.cs.mpgc.rpg125969.controller;

import it.unicam.cs.mpgc.rpg125969.model.match.Match;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchResult;
import it.unicam.cs.mpgc.rpg125969.model.match.TacticalChoice;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;

/**
 * Controller responsabile della gestione delle partite.
 * Coordina la simulazione dei turni e la raccolta del risultato finale.
 */
public class MatchController {

    private Match currentMatch;

    /**
     * Avvia una nuova partita tra la squadra di casa e quella ospite.
     *
     * @param home la squadra di casa
     * @param away la squadra ospite
     */
    public void startMatch(Team home, Team away) {
        this.currentMatch = new Match(home, away);
    }

    /**
     * Simula un turno della partita con la scelta tattica fornita.
     *
     * @param choice     la scelta tattica del manager
     * @param turnNumber il numero del turno (1, 2 o 3)
     */
    public void playTurn(TacticalChoice choice, int turnNumber) {
        if (currentMatch == null) return;
        currentMatch.simulateTurn(choice, turnNumber);
    }

    /**
     * Restituisce il risultato finale della partita corrente.
     *
     * @return il risultato della partita
     */
    public MatchResult getResult() {
        if (currentMatch == null) return null;
        return currentMatch.getResult();
    }

    /**
     * Restituisce la partita corrente.
     *
     * @return la partita in corso
     */
    public Match getCurrentMatch() {
        return currentMatch;
    }
}
