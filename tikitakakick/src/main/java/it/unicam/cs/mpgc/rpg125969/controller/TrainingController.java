package it.unicam.cs.mpgc.rpg125969.controller;

import it.unicam.cs.mpgc.rpg125969.model.player.Player;
import it.unicam.cs.mpgc.rpg125969.model.player.PlayerState;
import it.unicam.cs.mpgc.rpg125969.model.training.TrainingSession;
import it.unicam.cs.mpgc.rpg125969.model.training.TrainingType;

/**
 * Controller responsabile della gestione degli allenamenti.
 * Coordina le sessioni di allenamento tra il model e la view.
 */
public class TrainingController {

    /**
     * Avvia una sessione di allenamento per il giocatore specificato.
     * Se il giocatore è infortunato, l'allenamento non viene eseguito.
     *
     * @param player il giocatore da allenare
     * @param type   il tipo di allenamento da svolgere
     * @return true se l'allenamento è stato eseguito, false altrimenti
     */
    public boolean train(Player player, TrainingType type) {
        if (player.getState() == PlayerState.INJURED) {
            return false;
        }
        TrainingSession session = new TrainingSession(type, player);
        session.execute();
        return true;
    }

    /**
     * Ripristina lo stato del giocatore a FIT dopo un turno di riposo.
     *
     * @param player il giocatore da far riposare
     */
    public void rest(Player player) {
        if (player.getState() == PlayerState.TIRED) {
            player.setState(PlayerState.FIT);
        }
    }
}