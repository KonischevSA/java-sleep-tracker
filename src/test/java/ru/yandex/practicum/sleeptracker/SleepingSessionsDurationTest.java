package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepingSessionsDurationTest {

    static List<String> sessions_empty = new ArrayList<>();

    static List<String> sessions_good = new ArrayList<>(List.of(
            "01.10.25 23:59;02.10.25 00:00;GOOD",
            "02.10.25 22:00;03.10.25 09:00;NORMAL",
            "03.10.25 14:10;03.10.25 15:00;NORMAL",
            "03.10.25 23:00;04.10.25 09:00;BAD",
            "05.10.25 00:10;05.10.25 06:20;GOOD"
    ));

    static List<String> sessions_single = new ArrayList<>(List.of(
            "02.10.25 22:00;03.10.25 09:00;NORMAL"
    ));

    @Test
    public void test_emptySessionsListShouldReturn0MinDuration() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_empty);
        SleepingSessionsMinDuration func = new SleepingSessionsMinDuration();

        assertEquals(0, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_emptySessionsListShouldReturn0MaxDuration() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_empty);
        SleepingSessionsMaxDuration func = new SleepingSessionsMaxDuration();

        assertEquals(0, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_emptySessionsListShouldReturn0AvgDuration() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_empty);
        SleepingSessionsAvgDuration func = new SleepingSessionsAvgDuration();

        assertEquals(0.0, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_normalSessionListMinDurationShouldReturn1() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_good);
        SleepingSessionsMinDuration func = new SleepingSessionsMinDuration();

        assertEquals(1L, (func.apply(sessions).getResultValue()));
    }

    @Test
    public void test_normalSessionListMaxDurationShouldReturn660() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_good);
        SleepingSessionsMaxDuration func = new SleepingSessionsMaxDuration();

        assertEquals(660L, (func.apply(sessions).getResultValue()));
    }

    @Test
    public void test_normalSessionListAvgDurationShouldReturn336_2() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_good);
        SleepingSessionsAvgDuration func = new SleepingSessionsAvgDuration();

        assertEquals(336.2, (func.apply(sessions).getResultValue()));
    }

    @Test
    public void test_1RowSessionListMinDurationShouldReturn660() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single);
        SleepingSessionsMinDuration func = new SleepingSessionsMinDuration();

        assertEquals(660L, (func.apply(sessions).getResultValue()));
    }

    @Test
    public void test_1RowSessionListMaxDurationShouldReturn660() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single);
        SleepingSessionsMaxDuration func = new SleepingSessionsMaxDuration();

        assertEquals(660L, (func.apply(sessions).getResultValue()));
    }

    @Test
    public void test_1RowSessionListAvgDurationShouldReturn660() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single);
        SleepingSessionsAvgDuration func = new SleepingSessionsAvgDuration();

        assertEquals(660.0, (func.apply(sessions).getResultValue()));
    }
}
