package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionQuality;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepingSessionsBadQualityCount implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        Integer result = sessions.stream()
                .filter(ss -> ss.getSleepingQuality() == SleepingSessionQuality.BAD)
                .collect(Collectors.toSet()).size();

        return new SleepAnalysisResult("Количество сессий сна с плохим качеством в анализируемом периоде: " + result, result);
    }
}