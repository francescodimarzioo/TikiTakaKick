package it.unicam.cs.mpgc.rpg125969.model.player;

/**
 * Rappresenta lo stato fisico di un giocatore.
 * Lo stato influenza le prestazioni durante le partite
 * e la possibilità di partecipare agli allenamenti.
 */
public enum PlayerState {
    /** Il giocatore è in piena forma e disponibile. */
    FIT,
    /** Il giocatore è stanco e rende al di sotto delle sue capacità. */
    TIRED,
    /** Il giocatore è infortunato e non disponibile per le partite. */
    INJURED
}