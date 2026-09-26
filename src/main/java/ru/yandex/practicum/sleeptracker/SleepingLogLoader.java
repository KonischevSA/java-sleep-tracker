package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.exceptions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

public class SleepingLogLoader {

    public static ArrayList<SleepingSession> loadSessions(String logFile) throws IOException, DateTimeParseException,
            DateTimeParseChainException, SleepingQualityFormatException {

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {

            return br.lines()
                    .map(SleepingLogLoader::parseLine)
                    .collect(toCollection(ArrayList::new));
        }
    }

    /*Метод добавлен для удобства тестирования, чтобы плодить меньше файлов*/
    public static ArrayList<SleepingSession> loadSessions(List<String> sessionStrings) throws DateTimeParseException,
            DateTimeParseChainException, SleepingQualityFormatException {
        return sessionStrings.stream()
                .map(SleepingLogLoader::parseLine)
                .collect(toCollection(ArrayList::new));
    }

    private static SleepingSessionQuality getSleepingQuality(String qualityString) throws SleepingQualityFormatException {
        return switch (qualityString) {
            case "GOOD" -> SleepingSessionQuality.GOOD;
            case "NORMAL" -> SleepingSessionQuality.NORMAL;
            case "BAD" -> SleepingSessionQuality.BAD;
            default -> throw new SleepingQualityFormatException("Не удалось распознать значение качества сна. " +
                    "Доступные значения: GOOD, NORMAL, BAD. Получено: " + qualityString);
        };
    }

    private static SleepingSession parseLine(String line) throws DateTimeParseException, DateTimeParseChainException {
        try {
            String[] words = line.split(";");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
            LocalDateTime start = LocalDateTime.parse(words[0], formatter);
            LocalDateTime end = LocalDateTime.parse(words[1], formatter);

            if (!start.isBefore(end)) {
                throw new DateTimeParseChainException("Начало сессии сна должно быть раньше окончания");
            }

            return new SleepingSession(LocalDateTime.parse(words[0], formatter),
                    LocalDateTime.parse(words[1], formatter), getSleepingQuality(words[2]));
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException("Не удалось распознать формат даты в строке " + ex.getParsedString() +
                    ". Ожидаемый формат даты: dd.MM.yy HH:mm",
                    ex.getParsedString(),
                    ex.getErrorIndex());
        }
    }
}
