package it.unicam.cs.mpgc.rpg125969;

import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.PlayerState;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Player.
 * Verifica il corretto funzionamento delle statistiche e dello stato del giocatore.
 */
class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Vlahovic", 25, Role.ATTACKER, new Statistics(80, 85, 65, 40, 72));
    }

    @Test
    void testStatoInizialeDelGiocatore() {
        assertEquals(PlayerState.FIT, player.getState());
    }

    @Test
    void testCambioStatoGiocatore() {
        player.setState(PlayerState.TIRED);
        assertEquals(PlayerState.TIRED, player.getState());
    }

    @Test
    void testNomeGiocatore() {
        assertEquals("Vlahovic", player.getName());
    }

    @Test
    void testRuoloGiocatore() {
        assertEquals(Role.ATTACKER, player.getRole());
    }

    @Test
    void testStatisticheGiocatore() {
        assertEquals(80, player.getStatistics().getSpeed());
        assertEquals(85, player.getStatistics().getShooting());
    }

    @Test
    void testMiglioramentoStatistiche() {
        player.getStatistics().improveSpeed(5);
        assertEquals(85, player.getStatistics().getSpeed());
    }

    @Test
    void testStatisticheMassimo() {
        player.getStatistics().improveSpeed(100);
        assertEquals(99, player.getStatistics().getSpeed());
    }
}