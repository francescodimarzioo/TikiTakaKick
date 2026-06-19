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
     * Migliora la velocità del giocatore della quantità specificata.
     *
     * @param amount quantità da aggiungere alla velocità
     */
    public void improveSpeed(int amount) { speed = Math.min(99, speed + amount); }

    /**
     * Migliora la capacità di tiro del giocatore della quantità specificata.
     *
     * @param amount quantità da aggiungere al tiro
     */
    public void improveShooting(int amount) { shooting = Math.min(99, shooting + amount); }

    /**
     * Migliora la capacità di passaggio del giocatore della quantità specificata.
     *
     * @param amount quantità da aggiungere al passaggio
     */
    public void improvePassing(int amount) { passing = Math.min(99, passing + amount); }

    /**
     * Migliora la capacità difensiva del giocatore della quantità specificata.
     *
     * @param amount quantità da aggiungere alla difesa
     */
    public void improveDefense(int amount) { defense = Math.min(99, defense + amount); }

    /**
     * Migliora la resistenza fisica del giocatore della quantità specificata.
     *
     * @param amount quantità da aggiungere alla resistenza
     */
    public void improveStamina(int amount) { stamina = Math.min(99, stamina + amount); }
}