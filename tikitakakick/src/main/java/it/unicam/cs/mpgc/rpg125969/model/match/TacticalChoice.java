package it.unicam.cs.mpgc.rpg125969.model.match;

/**
 * Rappresenta la scelta tattica del manager durante un turno di partita.
 * Ogni scelta influenza la probabilità di generare eventi offensivi o difensivi.
 */
public enum TacticalChoice {
    /** Aumenta la probabilità di segnare ma espone maggiormente in difesa. */
    ATTACK,
    /** Riduce la probabilità di subire gol ma limita le occasioni offensive. */
    DEFEND,
    /** Bilancia le probabilità offensive e difensive. */
    BALANCED
}