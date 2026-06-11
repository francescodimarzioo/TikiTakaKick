package it.unicam.cs.mpgc.rpg125969.model.player;

/**
 * Rappresenta le statistiche atletiche di un giocatore.
 * Ogni attributo è limitato a un valore massimo di 99.
 */
public class Statistics {

    private int speed;
    private int shooting;
    private int passing;
    private int defense;
    private int stamina;

    /**
     * Costruisce un oggetto Statistics con i valori forniti.
     *
     * @param speed    velocità del giocatore (0-99)
     * @param shooting capacità di tiro (0-99)
     * @param passing  capacità di passaggio (0-99)
     * @param defense  capacità difensiva (0-99)
     * @param stamina  resistenza fisica (0-99)
     */
    public Statistics(int speed, int shooting, int passing, int defense, int stamina) {
        this.speed = speed;
        this.shooting = shooting;
        this.passing = passing;
        this.defense = defense;
        this.stamina = stamina;
    }

    public int getSpeed() { return speed; }
    public int getShooting() { return shooting; }
    public int getPassing() { return passing; }
    public int getDefense() { return defense; }
    public int getStamina() { return stamina; }

    /**
     * Migliora tutte le statistiche della quantità specificata.
     * Nessun attributo può superare il valore massimo di 99.
     *
     * @param amount quantità da aggiungere a ciascuna statistica
     */
    public void improve(int amount) {
        speed = Math.min(99, speed + amount);
        shooting = Math.min(99, shooting + amount);
        passing = Math.min(99, passing + amount);
        defense = Math.min(99, defense + amount);
        stamina = Math.min(99, stamina + amount);
    }
}