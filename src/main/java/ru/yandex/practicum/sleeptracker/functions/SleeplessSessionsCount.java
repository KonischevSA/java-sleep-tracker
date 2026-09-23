package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleeplessSessionsCount implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {

        int totalDays = sessions.stream()
                .collect(Collectors.groupingBy(ss -> ss.getSessionBeginning().toLocalDate()))
                .size();

        int totalNights = sessions.stream()
                .filter(SleepingSession::isNightSession)
                .collect(Collectors.groupingBy(ss -> ss.getSessionBeginning().toLocalDate()))
                .size();

        return new SleepAnalysisResult("Количество бессонных ночей за анализируемый период: " + (totalDays - totalNights), totalDays - totalNights);
    }
}
