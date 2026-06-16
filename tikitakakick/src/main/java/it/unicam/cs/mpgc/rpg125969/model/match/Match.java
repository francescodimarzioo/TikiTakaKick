package it.unicam.cs.mpgc.rpg125969.model.match;

import it.unicam.cs.mpgc.rpg125969.model.team.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Rappresenta una partita tra due squadre in TikiTakaKick.
 * La partita è divisa in 3 turni tattici (0-30, 30-60, 60-90 minuti).
 * Ogni turno il manager sceglie una tattica che influenza gli eventi generati.
 */
public class Match {

    private static final int TURNS = 3;
    private static final Random random = new Random();

    private final Team home;
    private final Team away;
    private final List<MatchEvent> events;
    private int homeGoals;
    private int awayGoals;

    /**
     * Costruisce una nuova partita tra la squadra di casa e quella ospite.
     *
     * @param home la squadra di casa
     * @param away la squadra ospite
     */
    public Match(Team home, Team away) {
        this.home = home;
        this.away = away;
        this.events = new ArrayList<>();
        this.homeGoals = 0;
        this.awayGoals = 0;
    }

    /**
     * Simula un turno della partita in base alla scelta tattica del manager.
     * Ogni turno copre 30 minuti di gioco.
     *
     * @param choice  la scelta tattica del manager per questo turno
     * @param turnNumber il numero del turno (1, 2 o 3)
     */
    public void simulateTurn(TacticalChoice choice, int turnNumber) {
        int startMinute = (turnNumber - 1) * 30 + 1;
        int endMinute = turnNumber * 30;

        double attackBonus = getAttackBonus(choice);
        double defendBonus = getDefendBonus(choice);

        int homeStrength = (int) (home.getOverallStrength() * attackBonus);
        int awayStrength = (int) (away.getOverallStrength() * defendBonus);

        int eventMinute = startMinute + random.nextInt(30);

        if (random.nextInt(100) < homeStrength) {
            homeGoals++;
            events.add(new MatchEvent(eventMinute, "GOL! " + home.getName() + " segna!", true));
        } else if (random.nextInt(100) < awayStrength) {
            awayGoals++;
            events.add(new MatchEvent(eventMinute, "GOL! " + away.getName() + " segna!", true));
        } else {
            events.add(new MatchEvent(eventMinute, "Occasione mancata.", false));
        }
    }

    /**
     * Restituisce il bonus offensivo in base alla scelta tattica.
     *
     * @param choice la scelta tattica
     * @return il moltiplicatore offensivo
     */
    private double getAttackBonus(TacticalChoice choice) {
        return switch (choice) {
            case ATTACK -> 1.3;
            case DEFEND -> 0.7;
            case BALANCED -> 1.0;
        };
    }

    /**
     * Restituisce il bonus difensivo in base alla scelta tattica.
     *
     * @param choice la scelta tattica
     * @return il moltiplicatore difensivo
     */
    private double getDefendBonus(TacticalChoice choice) {
        return switch (choice) {
            case ATTACK -> 1.1;
            case DEFEND -> 0.6;
            case BALANCED -> 0.9;
        };
    }

    /**
     * Restituisce il risultato finale della partita.
     *
     * @return oggetto MatchResult con i gol finali
     */
    public MatchEvent getResult() {
        return new MatchEvent(homeGoals, awayGoals);
    }

    public Team getHome() { return home; }
    public Team getAway() { return away; }
    public List<MatchEvent> getEvents() { return events; }
    public int getHomeGoals() { return homeGoals; }
    public int getAwayGoals() { return awayGoals; }
}