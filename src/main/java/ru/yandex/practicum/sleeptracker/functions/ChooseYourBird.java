package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChooseYourBird implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Поздравляем! Вы - Голубь", "Голубь");
        }

        Map<String, Long> birds = sessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(ss -> {
                    if ((ss.getSessionBeginning().toLocalTime().isAfter(LocalTime.of(23, 0)) ||
                            ss.getSessionBeginning().toLocalTime().equals(LocalTime.of(23, 0))) &&
                            (ss.getSessionEnd().toLocalTime().isAfter(LocalTime.of(9, 0)) ||
                                    ss.getSessionEnd().toLocalTime().equals(LocalTime.of(9, 0)))) {
                        return "Сова";
                    } else if ((ss.getSessionBeginning().toLocalTime().isBefore(LocalTime.of(22, 0)) ||
                            ss.getSessionBeginning().toLocalTime().equals(LocalTime.of(22, 0))) &&
                            (ss.getSessionEnd().toLocalTime().isBefore(LocalTime.of(7, 0)) ||
                                    ss.getSessionEnd().toLocalTime().equals(LocalTime.of(7, 0)))) {
                        return "Жаворонок";
                    } else {
                        return "Голубь";
                    }
                })
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        /*Вынес выбор наибольшего значения вместо getFirst(),
         * т.к. getFirst() не гарантирует, что остальные значения меньше.*/

        long owlCount = 0;
        long skylarkCount = 0;
        long PigeonCount = 0;
        String bird = "Голубь";

        if (birds.containsKey("Сова")) {
            owlCount = birds.get("Сова");
        }

        if (birds.containsKey("Жаворонок")) {
            skylarkCount = birds.get("Жаворонок");
        }

        if (birds.containsKey("Голубь")) {
            PigeonCount = birds.get("Голубь");
        }

        if (owlCount > skylarkCount) {
            if (owlCount > PigeonCount) {
                bird = "Сова";
            } else {
                bird = "Голубь";
            }
        } else if (skylarkCount > owlCount) {
            if (skylarkCount > PigeonCount) {
                bird = "Жаворонок";
            } else {
                bird = "Голубь";
            }
        }

        return new SleepAnalysisResult("Поздравляем! Вы - " + bird, bird);
    }
}
