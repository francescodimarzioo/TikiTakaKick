package it.unicam.cs.mpgc.rpg125969.util;

import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.Role;
import it.unicam.cs.mpgc.rpg125969.model.player.Statistics;
import it.unicam.cs.mpgc.rpg125969.model.team.Formation;
import it.unicam.cs.mpgc.rpg125969.model.team.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory responsabile della creazione delle squadre di Serie A.
 * Separa la responsabilità di creazione dei dati da GameContext,
 * rispettando il principio SRP di SOLID.
 */
public class TeamFactory {

    /**
     * Crea e restituisce tutte le squadre di Serie A
     * con le rose aggiornate alla stagione 2025/26.
     *
     * @return lista di tutte le squadre disponibili
     */
    public List<Team> createAllTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(createJuventus());
        teams.add(createInter());
        teams.add(createMilan());
        teams.add(createNapoli());
        teams.add(createRoma());
        return teams;
    }

    /**
     * Crea la squadra della Juventus.
     *
     * @return la squadra della Juventus con la rosa aggiornata
     */
    private Team createJuventus() {
        Team team = new Team("Juventus", Formation.F_4_3_3);
        team.addPlayer(new Player("Vlahovic",    25, Role.ATTACKER,   new Statistics(80, 87, 65, 40, 74)));
        team.addPlayer(new Player("Yildiz",      20, Role.ATTACKER,   new Statistics(82, 78, 76, 42, 78)));
        team.addPlayer(new Player("Locatelli",   27, Role.MIDFIELDER, new Statistics(72, 65, 84, 72, 80)));
        team.addPlayer(new Player("Gatti",       27, Role.DEFENDER,   new Statistics(70, 38, 62, 86, 80)));
        team.addPlayer(new Player("Di Gregorio", 28, Role.GOALKEEPER, new Statistics(52, 28, 56, 86, 76)));
        return team;
    }

    /**
     * Crea la squadra dell'Inter.
     *
     * @return la squadra dell'Inter con la rosa aggiornata
     */
    private Team createInter() {
        Team team = new Team("Inter", Formation.F_3_5_2);
        team.addPlayer(new Player("Lautaro", 27, Role.ATTACKER,   new Statistics(84, 88, 72, 44, 82)));
        team.addPlayer(new Player("Thuram",  27, Role.ATTACKER,   new Statistics(86, 80, 70, 42, 84)));
        team.addPlayer(new Player("Barella", 28, Role.MIDFIELDER, new Statistics(82, 72, 86, 74, 86)));
        team.addPlayer(new Player("Bastoni", 25, Role.DEFENDER,   new Statistics(76, 42, 74, 87, 82)));
        team.addPlayer(new Player("Sommer",  36, Role.GOALKEEPER, new Statistics(50, 28, 56, 88, 74)));
        return team;
    }

    /**
     * Crea la squadra del Milan.
     *
     * @return la squadra del Milan con la rosa aggiornata
     */
    private Team createMilan() {
        Team team = new Team("Milan", Formation.F_4_2_3_1);
        team.addPlayer(new Player("Gimenez",   24, Role.ATTACKER,   new Statistics(84, 84, 68, 42, 82)));
        team.addPlayer(new Player("Leao",      25, Role.ATTACKER,   new Statistics(92, 80, 76, 38, 84)));
        team.addPlayer(new Player("Reijnders", 26, Role.MIDFIELDER, new Statistics(80, 70, 84, 70, 82)));
        team.addPlayer(new Player("Thiaw",     24, Role.DEFENDER,   new Statistics(74, 38, 62, 86, 80)));
        team.addPlayer(new Player("Maignan",   29, Role.GOALKEEPER, new Statistics(54, 30, 58, 90, 80)));
        return team;
    }

    /**
     * Crea la squadra del Napoli.
     *
     * @return la squadra del Napoli con la rosa aggiornata
     */
    private Team createNapoli() {
        Team team = new Team("Napoli", Formation.F_4_3_3);
        team.addPlayer(new Player("Lukaku",        31, Role.ATTACKER,   new Statistics(82, 84, 66, 46, 80)));
        team.addPlayer(new Player("Kvaratskhelia", 24, Role.ATTACKER,   new Statistics(90, 84, 80, 40, 84)));
        team.addPlayer(new Player("Anguissa",      29, Role.MIDFIELDER, new Statistics(80, 68, 80, 76, 84)));
        team.addPlayer(new Player("Buongiorno",    25, Role.DEFENDER,   new Statistics(72, 38, 64, 88, 80)));
        team.addPlayer(new Player("Meret",         27, Role.GOALKEEPER, new Statistics(52, 28, 56, 86, 76)));
        return team;
    }

    /**
     * Crea la squadra della Roma.
     *
     * @return la squadra della Roma con la rosa aggiornata
     */
    private Team createRoma() {
        Team team = new Team("Roma", Formation.F_4_3_3);
        team.addPlayer(new Player("Dybala",     31, Role.ATTACKER,   new Statistics(84, 84, 82, 38, 74)));
        team.addPlayer(new Player("Dovbyk",     27, Role.ATTACKER,   new Statistics(80, 84, 64, 42, 78)));
        team.addPlayer(new Player("Pellegrini", 28, Role.MIDFIELDER, new Statistics(78, 72, 84, 66, 78)));
        team.addPlayer(new Player("Kone",       22, Role.DEFENDER,   new Statistics(74, 40, 70, 80, 78)));
        team.addPlayer(new Player("Svilar",     25, Role.GOALKEEPER, new Statistics(52, 28, 56, 84, 76)));
        return team;
    }
}