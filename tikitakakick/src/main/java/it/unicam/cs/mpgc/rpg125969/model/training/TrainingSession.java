package it.unicam.cs.mpgc.rpg125969.model.training;

import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.PlayerState;

/**
 * Rappresenta una sessione di allenamento assegnata a un giocatore.
 * L'allenamento migliora una statistica specifica ma affatica il giocatore.
 */
public class TrainingSession {

    private static final int IMPROVEMENT_AMOUNT = 2;

    private final TrainingType type;
    private final Player player;

    /**
     * Costruisce una nuova sessione di allenamento.
     *
     * @param type   il tipo di allenamento da svolgere
     * @param player il giocatore che svolge l'allenamento
     */
    public TrainingSession(TrainingType type, Player player) {
        this.type = type;
        this.player = player;
    }

    /**
     * Esegue la sessione di allenamento.
     * Migliora la statistica corrispondente al tipo di allenamento
     * e imposta lo stato del giocatore a TIRED.
     */
    public void execute() {
        if (player.getState() == PlayerState.INJURED) return;

        switch (type) {
            case SPEED -> player.getStatistics().improveSpeed(IMPROVEMENT_AMOUNT);
            case SHOOTING -> player.getStatistics().improveShooting(IMPROVEMENT_AMOUNT);
            case PASSING -> player.getStatistics().improvePassing(IMPROVEMENT_AMOUNT);
            case DEFENSE -> player.getStatistics().improveDefense(IMPROVEMENT_AMOUNT);
            case STAMINA -> player.getStatistics().improveStamina(IMPROVEMENT_AMOUNT);
        }

        player.setState(PlayerState.TIRED);
    }

    public TrainingType getType() { return type; }
    public Player getPlayer() { return player; }
}