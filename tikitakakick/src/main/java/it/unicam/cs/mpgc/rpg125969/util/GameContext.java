package it.unicam.cs.mpgc.rpg125969.util;

import it.unicam.cs.mpgc.rpg125969.model.league.League;
import it.unicam.cs.mpgc.rpg125969.model.league.Matchday;
import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import it.unicam.cs.mpgc.rpg125969.model.team.Formation;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import it.unicam.cs.mpgc.rpg125969.model.match.Match;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton che mantiene lo stato globale del gioco.
 * Contiene il riferimento alla squadra del giocatore e al campionato.
 */
public class GameContext {

    private static GameContext instance;
    private Team playerTeam;
    private League league;

    /**
     * Costruttore privato — inizializza il gioco con squadra di default.
     */
    private GameContext() {
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
     *
     * @param chosenTeamName il nome della squadra scelta
     */
    public void initializeWithTeam(String chosenTeamName) {
        List<Team> allTeams = createAllTeams();

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

    /**
     * Crea tutte le squadre con rose aggiornate al 2026.
     * Ogni squadra ha 5 giocatori rappresentativi.
     *
     * @return lista di tutte le squadre
     */
    private List<Team> createAllTeams() {
        List<Team> teams = new ArrayList<>();

        Team juventus = new Team("Juventus", Formation.F_4_3_3);
        juventus.addPlayer(new Player("Vlahovic",   25, Role.ATTACKER,   new Statistics(80, 87, 65, 40, 74)));
        juventus.addPlayer(new Player("Yildiz",     20, Role.ATTACKER,   new Statistics(82, 78, 76, 42, 78)));
        juventus.addPlayer(new Player("Locatelli",  27, Role.MIDFIELDER, new Statistics(72, 65, 84, 72, 80)));
        juventus.addPlayer(new Player("Gatti",      27, Role.DEFENDER,   new Statistics(70, 38, 62, 86, 80)));
        juventus.addPlayer(new Player("Di Gregorio",28, Role.GOALKEEPER, new Statistics(52, 28, 56, 86, 76)));
        teams.add(juventus);

        Team inter = new Team("Inter", Formation.F_3_5_2);
        inter.addPlayer(new Player("Lautaro",   27, Role.ATTACKER,   new Statistics(84, 88, 72, 44, 82)));
        inter.addPlayer(new Player("Thuram",    27, Role.ATTACKER,   new Statistics(86, 80, 70, 42, 84)));
        inter.addPlayer(new Player("Barella",   28, Role.MIDFIELDER, new Statistics(82, 72, 86, 74, 86)));
        inter.addPlayer(new Player("Bastoni",   25, Role.DEFENDER,   new Statistics(76, 42, 74, 87, 82)));
        inter.addPlayer(new Player("Sommer",    36, Role.GOALKEEPER, new Statistics(50, 28, 56, 88, 74)));
        teams.add(inter);

        Team milan = new Team("Milan", Formation.F_4_2_3_1);
        milan.addPlayer(new Player("Gimenez",    24, Role.ATTACKER,   new Statistics(84, 84, 68, 42, 82)));
        milan.addPlayer(new Player("Leao",       25, Role.ATTACKER,   new Statistics(92, 80, 76, 38, 84)));
        milan.addPlayer(new Player("Reijnders",  26, Role.MIDFIELDER, new Statistics(80, 70, 84, 70, 82)));
        milan.addPlayer(new Player("Thiaw",      24, Role.DEFENDER,   new Statistics(74, 38, 62, 86, 80)));
        milan.addPlayer(new Player("Maignan",    29, Role.GOALKEEPER, new Statistics(54, 30, 58, 90, 80)));
        teams.add(milan);

        Team napoli = new Team("Napoli", Formation.F_4_3_3);
        napoli.addPlayer(new Player("Lukaku",         31, Role.ATTACKER,   new Statistics(82, 84, 66, 46, 80)));
        napoli.addPlayer(new Player("De Bruine",  24, Role.ATTACKER,   new Statistics(90, 84, 80, 40, 84)));
        napoli.addPlayer(new Player("Anguissa",       29, Role.MIDFIELDER, new Statistics(80, 68, 80, 76, 84)));
        napoli.addPlayer(new Player("Buongiorno",     25, Role.DEFENDER,   new Statistics(72, 38, 64, 88, 80)));
        napoli.addPlayer(new Player("Meret",          27, Role.GOALKEEPER, new Statistics(52, 28, 56, 86, 76)));
        teams.add(napoli);

        Team roma = new Team("Roma", Formation.F_4_3_3);
        roma.addPlayer(new Player("Dybala",      31, Role.ATTACKER,   new Statistics(84, 84, 82, 38, 74)));
        roma.addPlayer(new Player("Dovbyk",      27, Role.ATTACKER,   new Statistics(80, 84, 64, 42, 78)));
        roma.addPlayer(new Player("Pellegrini",  28, Role.MIDFIELDER, new Statistics(78, 72, 84, 66, 78)));
        roma.addPlayer(new Player("Kone",     37, Role.DEFENDER,   new Statistics(64, 36, 72, 86, 68)));
        roma.addPlayer(new Player("Svilar",      25, Role.GOALKEEPER, new Statistics(52, 28, 56, 84, 76)));
        teams.add(roma);

        return teams;
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