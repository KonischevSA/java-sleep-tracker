package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.function.Function;

public class SleepingSessionsAvgDuration implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        OptionalDouble avgDuration = sessions.stream()
                .map(SleepingSession::getSessionDuration)
                .mapToDouble(Duration::toMinutes)
                .average();


        if (avgDuration.isPresent()) {
            return new SleepAnalysisResult("Средняя продолжительность сна за анализируемый период: " + String.format("%.2f", avgDuration.getAsDouble()),
                    avgDuration.getAsDouble());
        } else {
            return new SleepAnalysisResult("Средняя продолжительность сна за анализируемый период: " + 0.0, 0.0);
        }
    }
}