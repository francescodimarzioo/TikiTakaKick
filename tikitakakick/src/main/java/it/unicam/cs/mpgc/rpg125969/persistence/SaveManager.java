package it.unicam.cs.mpgc.rpg125969.persistence;

import it.unicam.cs.mpgc.rpg125969.model.league.League;
import java.io.IOException;

/**
 * Interfaccia per la gestione del salvataggio e caricamento della partita.
 * Permette di estendere la persistenza con diversi formati (JSON, XML, DB...).
 */
public interface SaveManager {

    /**
     * Salva lo stato corrente del campionato.
     *
     * @param league il campionato da salvare
     * @throws IOException se si verifica un errore durante il salvataggio
     */
    void save(League league) throws IOException;

    /**
     * Carica lo stato del campionato da un salvataggio.
     *
     * @param fileName il nome del file di salvataggio
     * @return il campionato caricato
     * @throws IOException se si verifica un errore durante il caricamento
     */
    League load(String fileName) throws IOException;
}
