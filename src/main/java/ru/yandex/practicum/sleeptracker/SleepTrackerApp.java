package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.functions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    static String sleepingLogFile = "src/main/resources/sleep_log.txt";
    static List<SleepingSession> sleepingSessions;

    public static void main(String[] args) throws IOException {
        sleepingSessions = SleepingLogLoader.loadSessions(sleepingLogFile);

        ArrayList<Function<ArrayList<SleepingSession>, SleepAnalysisResult>> functions = new ArrayList<>();

        functions.add(new SleepingSessionsCount());
        functions.add(new SleepingSessionsMinDuration());
        functions.add(new SleepingSessionsMaxDuration());
        functions.add(new SleepingSessionsAvgDuration());
        functions.add(new SleepingSessionsBadQualityCount());
        functions.add(new SleeplessSessionsCount());
        functions.add(new ChooseYourBird());

        functions.forEach(function ->
                System.out.println(function.apply((ArrayList<SleepingSession>) sleepingSessions).getResultMessage()));
    }
}