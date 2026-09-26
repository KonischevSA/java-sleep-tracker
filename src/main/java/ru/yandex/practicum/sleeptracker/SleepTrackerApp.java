package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exceptions.DateTimeParseChainException;
import ru.yandex.practicum.sleeptracker.exceptions.SleepingQualityFormatException;
import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    static List<SleepingSession> sleepingSessions;

    public static void main(String[] args) {

        if (args.length > 0 && Files.exists(Path.of(args[0]))) {
            try {
                sleepingSessions = SleepingLogLoader.loadSessions(args[0]);
            } catch (DateTimeParseException | SleepingQualityFormatException | DateTimeParseChainException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Ошибка загрузки файла с сессиями сна");
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Не удалось получить имя файла из аргументов командной строки");
            return;
        }

        ArrayList<Function<ArrayList<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

        functions.add(new SleepingSessionsCount());
        functions.add(new SleepingSessionsMinDuration());
        functions.add(new SleepingSessionsMaxDuration());
        functions.add(new SleepingSessionsAvgDuration());
        functions.add(new SleepingSessionsBadQualityCount());
        functions.add(new SleeplessSessionsCount());
        functions.add(new ChooseYourBird());

        functions.forEach(function ->
                System.out.println(function.apply((ArrayList<SleepingSession>) sleepingSessions).getResultMessage()));
    }
}