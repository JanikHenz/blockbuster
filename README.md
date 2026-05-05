# Blockbuster

Eine Android-App (Kotlin + Jetpack Compose) fuer Film- und Serieninhalte mit Daten aus einer TMDB-Anbindung und einer UI fuer Uebersicht sowie Detailansichten.

## Voraussetzungen

- Android Studio (aktuelle Version)
- JDK 17
- Android SDK gemaess Gradle-Konfiguration

## Projekt starten

```bash
./gradlew assembleDebug
```

Oder in Android Studio direkt ein Emulator-/Geraete-Target auswaehlen und starten.

## Projektstruktur

- `app/src/main/kotlin/fhnw/emoba/blockbuster`: App-Logik, Datenmodelle und UI
- `app/src/main/res`: Ressourcen (Strings, Themes, Drawables)
