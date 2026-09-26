package ru.yandex.practicum.sleeptracker.functions;

import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.ArrayList;
import java.util.function.Function;

public class SleepingSessionsCount implements Function<ArrayList<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(ArrayList<SleepingSession> sessions) {
        return new SleepAnalysisResult("Количество сессий сна в анализируемом периоде: " + sessions.size(), sessions.size());
    }
}
