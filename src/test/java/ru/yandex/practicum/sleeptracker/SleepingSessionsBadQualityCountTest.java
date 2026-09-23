package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SleepingSessionsBadQualityCount;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleepingSessionsBadQualityCountTest {

    static List<String> sessions_empty = new ArrayList<>();

    static List<String> sessions_good = new ArrayList<>(List.of(
            "01.10.25 23:59;02.10.25 00:00;GOOD",
            "02.10.25 22:00;03.10.25 09:00;BAD",
            "03.10.25 14:10;03.10.25 15:00;NORMAL",
            "03.10.25 23:00;04.10.25 09:00;BAD",
            "05.10.25 00:10;05.10.25 06:20;GOOD"
    ));

    static List<String> sessions_single_normal = new ArrayList<>(List.of(
            "02.10.25 22:00;03.10.25 09:00;NORMAL"
    ));

    static List<String> sessions_single_bad = new ArrayList<>(List.of(
            "02.10.25 22:00;03.10.25 09:00;BAD"
    ));

    @Test
    public void test_emptySessionsListShouldReturn0() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_empty);
        SleepingSessionsBadQualityCount func = new SleepingSessionsBadQualityCount();

        assertEquals(0, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_1NormalQualityRowSessionsListShouldReturn0() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_normal);
        SleepingSessionsBadQualityCount func = new SleepingSessionsBadQualityCount();

        assertEquals(0, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_1BadQualityRowSessionsListShouldReturn1() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_bad);
        SleepingSessionsBadQualityCount func = new SleepingSessionsBadQualityCount();

        assertEquals(1, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_normalQualityRowSessionsListShouldReturn2() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_good);
        SleepingSessionsBadQualityCount func = new SleepingSessionsBadQualityCount();

        assertEquals(2, func.apply(sessions).getResultValue());
    }
}
