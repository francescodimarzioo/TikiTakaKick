package it.unicam.cs.mpgc.rpg125969;

import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import it.unicam.cs.mpgc.rpg125969.model.team.Formation;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test per la classe Team.
 * Verifica la gestione della rosa e il calcolo della forza complessiva.
 */
class TeamTest {

    private Team team;
    private Player player1;
    private Player player2;

    @BeforeEach
    void setUp() {
        team = new Team("Juventus", Formation.F_4_3_3);
        player1 = new Player("Vlahovic", 25, Role.ATTACKER, new Statistics(80, 85, 65, 40, 72));
        player2 = new Player("Locatelli", 27, Role.MIDFIELDER, new Statistics(72, 65, 84, 72, 80));
    }

    @Test
    void testNomeSquadra() {
        assertEquals("Juventus", team.getName());
    }

    @Test
    void testRosaVuotaInizialmente() {
        assertTrue(team.getRoster().isEmpty());
    }

    @Test
    void testAggiuntaGiocatore() {
        team.addPlayer(player1);
        assertEquals(1, team.getRoster().size());
    }

    @Test
    void testRimozionGiocatore() {
        team.addPlayer(player1);
        team.removePlayer(player1);
        assertTrue(team.getRoster().isEmpty());
    }

    @Test
    void testForzaSquadraVuota() {
        assertEquals(0, team.getOverallStrength());
    }

    @Test
    void testForzaSquadraConGiocatori() {
        team.addPlayer(player1);
        team.addPlayer(player2);
        assertTrue(team.getOverallStrength() > 0);
    }

    @Test
    void testCambioFormazione() {
        team.setFormation(Formation.F_4_4_2);
        assertEquals(Formation.F_4_4_2, team.getFormation());
    }
}