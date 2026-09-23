package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Function;

public class SleepingSessionsMinDuration implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        Optional<Duration> minDuration = sessions.stream()
                .map(SleepingSession::getSessionDuration)
                .min(Duration::compareTo);

        return minDuration.map(duration -> new SleepAnalysisResult("Наименьшая продолжительность сна (мин.): " + duration.toMinutes()
                , duration.toMinutes())).orElseGet(() -> new SleepAnalysisResult("Наименьшая продолжительность сна (мин.): " + 0, 0));
    }
}
