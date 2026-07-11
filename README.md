# TikiTakaKick ⚽

Gioco di ruolo gestionale a tema calcistico sviluppato in Java.
Assume il ruolo di manager di una squadra di Serie A e guidala al titolo!

## Descrizione

TikiTakaKick è un RPG gestionale in cui il giocatore sceglie una squadra di Serie A,
allena i propri giocatori, affronta le giornate del campionato e prende decisioni
tattiche durante le partite simulate in tempo reale.

## Requisiti

- Java 21 o superiore
- Connessione internet per il primo download delle dipendenze Gradle

## Avvio

```bash
./gradlew build
./gradlew run
```

## Funzionalità principali

- Scelta della squadra tra Juventus, Inter, Milan, Napoli e Roma
- Allenamento dei giocatori per migliorarne le statistiche
- Partite simulate in tempo reale con eventi casuali
- Cambio tattica durante la partita (Attacco, Bilanciato, Difesa)
- Classifica aggiornata automaticamente dopo ogni partita

## Architettura

Il progetto segue il pattern MVC con i seguenti package:
- **model/** — entità di dominio (player, team, match, league, training)
- **controller/** — logica di gioco (MatchController, LeagueController, TrainingController)
- **view/** — interfaccia grafica JavaFX
- **persistence/** — salvataggio JSON tramite interfaccia SaveManager
- **util/** — GameContext (Singleton) e TeamFactory (Factory)

## Pattern di progettazione

- **Singleton** — GameContext
- **Factory** — TeamFactory
- **Strategy** — TacticalChoice
- **Observer** — Timeline JavaFX per aggiornamento UI

## Tecnologie

- Java 21
- JavaFX 21
- Gson 2.10.1
- JUnit 5
- Gradle 9

## Dichiarazione uso AI

Durante lo sviluppo è stato utilizzato Claude (Anthropic) come strumento di supporto
per la progettazione dell'architettura, la generazione e correzione del codice,
la risoluzione di errori e la stesura della documentazione.
Per i dettagli completi vedere la [Wiki](../../wiki/Dichiarazione-AI).

## Autore

Francesco Di Marzio — matricola 125969
Università di Camerino — AA 2025/26
