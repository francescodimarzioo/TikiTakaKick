package it.unicam.cs.mpgc.rpg125969.util;

import it.unicam.cs.mpgc.rpg125969.model.league.League;
import it.unicam.cs.mpgc.rpg125969.model.league.Matchday;
import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import it.unicam.cs.mpgc.rpg125969.model.team.Formation;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import it.unicam.cs.mpgc.rpg125969.model.match.Match;

/**
 * Singleton che mantiene lo stato globale del gioco.
 * Contiene il riferimento alla squadra del giocatore e al campionato.
 */
public class GameContext {

    private static GameContext instance;
    private Team playerTeam;
    private League league;

    /**
     * Costruttore privato — inizializza il gioco con dati di default.
     */
    private GameContext() {
        initializeGame();
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
     * Inizializza il campionato con squadre e giocatori di default.
     */
    private void initializeGame() {
        // Crea la squadra del giocatore
        playerTeam = new Team("Juventus", Formation.F_4_3_3);
        playerTeam.addPlayer(new Player("Vlahovic", 24, Role.ATTACKER, new Statistics(78, 85, 65, 40, 72)));
        playerTeam.addPlayer(new Player("Yldiz", 26, Role.ATTACKER, new Statistics(88, 78, 72, 45, 80)));
        playerTeam.addPlayer(new Player("Locatelli", 25, Role.MIDFIELDER, new Statistics(70, 65, 82, 70, 78)));
        playerTeam.addPlayer(new Player("Bremer", 26, Role.DEFENDER, new Statistics(72, 40, 65, 88, 80)));
        playerTeam.addPlayer(new Player("Perin", 33, Role.GOALKEEPER, new Statistics(50, 30, 55, 88, 75)));

        // Crea le squadre avversarie
        Team inter = new Team("Inter", Formation.F_3_5_2);
        inter.addPlayer(new Player("Lautaro", 26, Role.ATTACKER, new Statistics(82, 88, 70, 42, 80)));
        inter.addPlayer(new Player("Barella", 27, Role.MIDFIELDER, new Statistics(80, 72, 85, 72, 85)));

        Team milan = new Team("Milan", Formation.F_4_4_2);
        milan.addPlayer(new Player("Leao", 24, Role.ATTACKER, new Statistics(92, 80, 75, 38, 82)));
        milan.addPlayer(new Player("Gimenez", 26, Role.DEFENDER, new Statistics(85, 70, 72, 78, 85)));

        Team napoli = new Team("Napoli", Formation.F_4_3_3);
        napoli.addPlayer(new Player("De Bruyne", 25, Role.ATTACKER, new Statistics(90, 88, 65, 35, 85)));

        Team roma = new Team("Roma", Formation.F_4_3_3);
        roma.addPlayer(new Player("Dybala", 30, Role.ATTACKER, new Statistics(85, 82, 80, 38, 75)));

        // Crea il campionato
        league = new League("Serie A");
        league.addTeam(playerTeam);
        league.addTeam(inter);
        league.addTeam(milan);
        league.addTeam(napoli);
        league.addTeam(roma);

        // Crea le giornate
        for (int i = 1; i <= 38; i++) {
            Matchday matchday = new Matchday(i);
            matchday.addMatch(new Match(playerTeam, inter));
            matchday.addMatch(new Match(milan, napoli));
            matchday.addMatch(new Match(roma, milan));
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