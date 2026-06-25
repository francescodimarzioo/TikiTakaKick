package it.unicam.cs.mpgc.rpg125969;

import it.unicam.cs.mpgc.rpg125969.model.match.Match;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchResult;
import it.unicam.cs.mpgc.rpg125969.model.match.TacticalChoice;
import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import it.unicam.cs.mpgc.rpg125969.model.team.Formation;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Match e MatchResult.
 * Verifica la simulazione dei turni e il calcolo del risultato finale.
 */
class MatchTest {

    private Team home;
    private Team away;
    private Match match;

    @BeforeEach
    void setUp() {
        home = new Team("Juventus", Formation.F_4_3_3);
        home.addPlayer(new Player("Vlahovic", 25, Role.ATTACKER, new Statistics(80, 85, 65, 40, 72)));
        home.addPlayer(new Player("Locatelli", 27, Role.MIDFIELDER, new Statistics(72, 65, 84, 72, 80)));

        away = new Team("Inter", Formation.F_3_5_2);
        away.addPlayer(new Player("Lautaro", 27, Role.ATTACKER, new Statistics(84, 88, 72, 44, 82)));
        away.addPlayer(new Player("Barella", 28, Role.MIDFIELDER, new Statistics(82, 72, 86, 74, 86)));

        match = new Match(home, away);
    }

    @Test
    void testSquadrePartita() {
        assertEquals("Juventus", match.getHome().getName());
        assertEquals("Inter", match.getAway().getName());
    }

    @Test
    void testEventiVuotiInizialmente() {
        assertTrue(match.getEvents().isEmpty());
    }

    @Test
    void testSimulazioneTurno() {
        match.simulateTurn(TacticalChoice.BALANCED, 1);
        assertFalse(match.getEvents().isEmpty());
    }

    @Test
    void testRisultatoInizialeZeroZero() {
        MatchResult result = match.getResult();
        assertEquals(0, result.getHomeGoals());
        assertEquals(0, result.getAwayGoals());
    }

    @Test
    void testMatchResultVittoriaCasa() {
        MatchResult result = new MatchResult(2, 0);
        assertTrue(result.isHomeWin());
        assertFalse(result.isDraw());
    }

    @Test
    void testMatchResultPareggio() {
        MatchResult result = new MatchResult(1, 1);
        assertTrue(result.isDraw());
        assertFalse(result.isHomeWin());
    }

    @Test
    void testMatchResultVittoriaOspite() {
        MatchResult result = new MatchResult(0, 2);
        assertFalse(result.isHomeWin());
        assertFalse(result.isDraw());
    }

    @Test
    void testTreSimulazioni() {
        match.simulateTurn(TacticalChoice.ATTACK, 1);
        match.simulateTurn(TacticalChoice.BALANCED, 2);
        match.simulateTurn(TacticalChoice.DEFEND, 3);
        assertEquals(3, match.getEvents().size());
    }
}