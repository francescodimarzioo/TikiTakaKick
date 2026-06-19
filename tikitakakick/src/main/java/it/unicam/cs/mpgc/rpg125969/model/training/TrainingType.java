package it.unicam.cs.mpgc.rpg125969.model.training;

/**
 * Rappresenta il tipo di allenamento che può essere assegnato a un giocatore.
 * Ogni tipo migliora una statistica specifica del giocatore.
 */
public enum TrainingType {
    /** Migliora la velocità del giocatore. */
    SPEED,
    /** Migliora la capacità di tiro del giocatore. */
    SHOOTING,
    /** Migliora la capacità di passaggio del giocatore. */
    PASSING,
    /** Migliora la capacità difensiva del giocatore. */
    DEFENSE,
    /** Migliora la resistenza fisica del giocatore. */
    STAMINA
}
