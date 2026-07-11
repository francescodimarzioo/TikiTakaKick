package it.unicam.cs.mpgc.rpg125969;

import it.unicam.cs.mpgc.rpg125969.controller.LeagueController;
import it.unicam.cs.mpgc.rpg125969.controller.MatchController;
import it.unicam.cs.mpgc.rpg125969.controller.TrainingController;
import it.unicam.cs.mpgc.rpg125969.model.league.League;
import it.unicam.cs.mpgc.rpg125969.model.league.TeamStanding;
import it.unicam.cs.mpgc.rpg125969.model.match.Match;
import it.unicam.cs.mpgc.rpg125969.model.match.MatchResult;
import it.unicam.cs.mpgc.rpg125969.model.match.TacticalChoice;
import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.PlayerState;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import it.unicam.cs.mpgc.rpg125969.model.team.Formation;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import it.unicam.cs.mpgc.rpg125969.model.training.TrainingType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la logica di gioco di TikiTakaKick.
 * Verifica il comportamento dei controller e delle interazioni tra le classi.
 */
class MatchTest {

    private Team home;
    private Team away;
    private Match match;
    private MatchController matchController;
    private TrainingController trainingController;
    private League league;
    private LeagueController leagueController;

    @BeforeEach
    void setUp() {
        home = new Team("Juventus", Formation.F_4_3_3);
        home.addPlayer(new Player("Vlahovic", 25, Role.ATTACKER, new Statistics(80, 85, 65, 40, 72)));
        home.addPlayer(new Player("Locatelli", 27, Role.MIDFIELDER, new Statistics(72, 65, 84, 72, 80)));

        away = new Team("Inter", Formation.F_3_5_2);
        away.addPlayer(new Player("Lautaro", 27, Role.ATTACKER, new Statistics(84, 88, 72, 44, 82)));
        away.addPlayer(new Player("Barella", 28, Role.MIDFIELDER, new Statistics(82, 72, 86, 74, 86)));

        match = new Match(home, away);
        matchController = new MatchController();
        trainingController = new TrainingController();

        league = new League("Serie A");
        league.addTeam(home);
        league.addTeam(away);
        leagueController = new LeagueController(league);
    }

    // Verifica che dopo un turno ci sia almeno un evento generato
    @Test
    void testSimulazioneTurnoGeneraEventi() {
        match.simulateTurn(TacticalChoice.BALANCED, 1);
        assertFalse(match.getEvents().isEmpty());
    }

    // Verifica che tre turni generino esattamente tre eventi
    @Test
    void testTreTurniGeneranoTreEventi() {
        match.simulateTurn(TacticalChoice.ATTACK, 1);
        match.simulateTurn(TacticalChoice.BALANCED, 2);
        match.simulateTurn(TacticalChoice.DEFEND, 3);
        assertEquals(3, match.getEvents().size());
    }

    // Verifica che il risultato iniziale sia 0-0
    @Test
    void testRisultatoInizialeZeroZero() {
        MatchResult result = match.getResult();
        assertEquals(0, result.getHomeGoals());
        assertEquals(0, result.getAwayGoals());
    }

    // Verifica che una vittoria in casa venga riconosciuta correttamente
    @Test
    void testVittoriaCasaRiconosciuta() {
        MatchResult result = new MatchResult(2, 0);
        assertTrue(result.isHomeWin());
        assertFalse(result.isDraw());
    }

    // Verifica che un pareggio venga riconosciuto correttamente
    @Test
    void testPareggioRiconosciuto() {
        MatchResult result = new MatchResult(1, 1);
        assertTrue(result.isDraw());
        assertFalse(result.isHomeWin());
    }

    // Verifica che una sconfitta venga riconosciuta correttamente
    @Test
    void testSconfittaRiconosciuta() {
        MatchResult result = new MatchResult(0, 3);
        assertFalse(result.isHomeWin());
        assertFalse(result.isDraw());
    }

    // Verifica che il MatchController avvii correttamente una partita
    @Test
    void testMatchControllerAvviaPartita() {
        matchController.startMatch(home, away);
        assertNotNull(matchController.getCurrentMatch());
        assertEquals("Juventus", matchController.getCurrentMatch().getHome().getName());
        assertEquals("Inter", matchController.getCurrentMatch().getAway().getName());
    }

    // Verifica che il MatchController giochi un turno e aggiorni il risultato
    @Test
    void testMatchControllerGiocaTurno() {
        matchController.startMatch(home, away);
        matchController.playTurn(TacticalChoice.BALANCED, 1);
        assertNotNull(matchController.getResult());
    }

    // Verifica che una vittoria aggiorni correttamente la classifica
    @Test
    void testVittoriaAggiornaClassifica() {
        MatchResult result = new MatchResult(2, 0);
        leagueController.updateStandings(home, away, result);
        TeamStanding standing = leagueController.getStandings().stream()
                .filter(s -> s.getTeam().equals(home))
                .findFirst().orElseThrow();
        assertEquals(3, standing.getPoints());
        assertEquals(1, standing.getWon());
    }

    // Verifica che un pareggio aggiorni correttamente la classifica
    @Test
    void testPareggioAggiornaClassifica() {
        MatchResult result = new MatchResult(1, 1);
        leagueController.updateStandings(home, away, result);
        TeamStanding standing = leagueController.getStandings().stream()
                .filter(s -> s.getTeam().equals(home))
                .findFirst().orElseThrow();
        assertEquals(1, standing.getPoints());
        assertEquals(1, standing.getDrawn());
    }

    // Verifica che una sconfitta non assegni punti
    @Test
    void testSconfittaNonAssegnaPunti() {
        MatchResult result = new MatchResult(0, 2);
        leagueController.updateStandings(home, away, result);
        TeamStanding standing = leagueController.getStandings().stream()
                .filter(s -> s.getTeam().equals(home))
                .findFirst().orElseThrow();
        assertEquals(0, standing.getPoints());
        assertEquals(1, standing.getLost());
    }

    // Verifica che l'allenamento migliori la statistica del giocatore
    @Test
    void testAllenamentoMiglioraStatistica() {
        Player player = home.getRoster().get(0);
        int speedBefore = player.getStatistics().getSpeed();
        trainingController.train(player, TrainingType.SPEED);
        assertTrue(player.getStatistics().getSpeed() > speedBefore);
    }

    // Verifica che l'allenamento porti il giocatore allo stato TIRED
    @Test
    void testAllenamentoPortaATired() {
        Player player = home.getRoster().get(0);
        trainingController.train(player, TrainingType.SPEED);
        assertEquals(PlayerState.TIRED, player.getState());
    }

    // Verifica che un giocatore infortunato non possa allenarsi
    @Test
    void testGiocatoreInfortunatoNonSiAllena() {
        Player player = home.getRoster().get(0);
        player.setState(PlayerState.INJURED);
        boolean result = trainingController.train(player, TrainingType.SPEED);
        assertFalse(result);
    }

    // Verifica che il riposo riporti il giocatore allo stato FIT
    @Test
    void testRiposoRiportaAFit() {
        Player player = home.getRoster().get(0);
        player.setState(PlayerState.TIRED);
        trainingController.rest(player);
        assertEquals(PlayerState.FIT, player.getState());
    }

    // Verifica che la giornata avanzi correttamente
    @Test
    void testAvanzamentoGiornata() {
        int before = league.getCurrentMatchday();
        leagueController.nextMatchday();
        assertEquals(before, league.getCurrentMatchday()); // non avanza se non ci sono giornate
    }
}