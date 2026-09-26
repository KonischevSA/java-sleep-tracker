package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.SleepingSessionQuality;

import java.util.ArrayList;
import java.util.function.Function;

public class SleepingSessionsBadQualityCount implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        Long result = sessions.stream()
                .filter(ss -> ss.getSleepingQuality() == SleepingSessionQuality.BAD)
                .count();

        return new SleepAnalysisResult("Количество сессий сна с плохим качеством в анализируемом периоде: " + result, result);
    }
}