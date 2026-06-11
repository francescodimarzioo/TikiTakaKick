package it.unicam.cs.mpgc.rpg125969.model.player;

/**
 * Rappresenta un calciatore in TikiTakaKick.
 * Ogni giocatore ha un nome, un'età, un ruolo, delle statistiche
 * e uno stato fisico che può cambiare nel corso della stagione.
 */
public class Player {

    private final String name;
    private final int age;
    private final Role role;
    private final Statistics statistics;
    private PlayerState state;

    /**
     * Costruisce un nuovo giocatore con gli attributi forniti.
     * Lo stato iniziale del giocatore è impostato a FIT.
     *
     * @param name       nome completo del giocatore
     * @param age        età del giocatore
     * @param role       ruolo del giocatore in campo
     * @param statistics statistiche iniziali del giocatore
     */
    public Player(String name, int age, Role role, Statistics statistics) {
        this.name = name;
        this.age = age;
        this.role = role;
        this.statistics = statistics;
        this.state = PlayerState.FIT;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public Role getRole() { return role; }
    public Statistics getStatistics() { return statistics; }
    public PlayerState getState() { return state; }

    /**
     * Aggiorna lo stato fisico del giocatore.
     *
     * @param state il nuovo stato fisico
     */
    public void setState(PlayerState state) { this.state = state; }

    @Override
    public String toString() {
        return name + " (" + role + ") - " + state;
    }
}