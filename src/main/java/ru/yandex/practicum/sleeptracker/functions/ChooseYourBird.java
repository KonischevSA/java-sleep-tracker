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
        return sessions.stream()
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
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted((o1, o2) -> Math.toIntExact(o2.getValue() - o1.getValue()))
                .map(Map.Entry::getKey)
                .findFirst()
                .map(s -> new SleepAnalysisResult("Поздравляем! Вы - " + s, s))
                .orElseGet(() -> new SleepAnalysisResult("Вероятно, Вы - Голубь", "Голубь"));
    }
}
