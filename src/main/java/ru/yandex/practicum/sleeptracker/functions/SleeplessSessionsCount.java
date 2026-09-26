package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.function.Function;

public class SleeplessSessionsCount implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей за анализируемый период: " + 0, 0L);
        }

        LocalDate firstDay = sessions.getFirst().getSessionBeginning().toLocalDate();

        if (sessions.getFirst().getSessionBeginning().toLocalTime().isBefore(LocalTime.of(12, 0))) {
            firstDay = firstDay.minusDays(1);
        }

        long totalDays = Period.between(firstDay, sessions.getLast().getSessionBeginning().toLocalDate().plusDays(1)).getDays();

        long totalNights = sessions.stream()
                .filter(SleepingSession::isNightSession)
                .map(ss -> ss.getSessionBeginning().toLocalDate())
                .distinct()
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей за анализируемый период: " + (totalDays - totalNights), totalDays - totalNights);
    }
}
