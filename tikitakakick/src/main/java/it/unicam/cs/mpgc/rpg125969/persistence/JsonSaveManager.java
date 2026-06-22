package it.unicam.cs.mpgc.rpg125969.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.mpgc.rpg125969.model.league.League;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Implementazione di SaveManager che salva e carica il campionato in formato JSON.
 * Utilizza la libreria Gson per la serializzazione e deserializzazione.
 */
public class JsonSaveManager implements SaveManager {

    private static final String SAVE_DIRECTORY = "saves";
    private final Gson gson;

    /**
     * Costruisce un nuovo JsonSaveManager.
     * Inizializza Gson con la formattazione pretty printing.
     */
    public JsonSaveManager() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    /**
     * Salva il campionato su file JSON nella cartella saves/.
     *
     * @param league il campionato da salvare
     * @throws IOException se si verifica un errore durante il salvataggio
     */
    @Override
    public void save(League league) throws IOException {
        Path saveDir = Paths.get(SAVE_DIRECTORY);
        Files.createDirectories(saveDir);
        Path filePath = saveDir.resolve(league.getName() + ".json");
        Files.writeString(filePath, gson.toJson(league));
    }

    /**
     * Carica il campionato da un file JSON nella cartella saves/.
     *
     * @param fileName il nome del file di salvataggio (senza estensione)
     * @return il campionato caricato
     * @throws IOException se il file non esiste o si verifica un errore
     */
    @Override
    public League load(String fileName) throws IOException {
        Path filePath = Paths.get(SAVE_DIRECTORY).resolve(fileName + ".json");
        if (!Files.exists(filePath)) {
            throw new IOException("File di salvataggio non trovato: " + fileName);
        }
        String json = Files.readString(filePath);
        return gson.fromJson(json, League.class);
    }
}