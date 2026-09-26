package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.SleeplessSessionsCount;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessSessionsCountTest {

    static List<String> sessions_empty = new ArrayList<>();

    static List<String> sessions_good = new ArrayList<>(List.of(
            "01.10.25 23:15;02.10.25 09:30;GOOD",
            "02.10.25 23:50;03.10.25 04:40;NORMAL",
            "03.10.25 14:10;03.10.25 15:00;NORMAL",
            "04.10.25 03:40;04.10.25 05:00;BAD",
            "05.10.25 00:10;05.10.25 06:20;GOOD",
            "05.10.25 17:30;06.10.25 00:15;NORMAL"
    ));

    static List<String> sessions_long_sleepless = new ArrayList<>(List.of(
            "01.10.25 23:15;02.10.25 09:30;GOOD",
            "11.10.25 23:50;12.10.25 04:40;NORMAL"
    ));

    static List<String> sessions_all_night = new ArrayList<>(List.of(
            "01.10.25 23:15;02.10.25 09:30;GOOD",
            "02.10.25 23:50;03.10.25 04:40;NORMAL",
            "03.10.25 23:50;04.10.25 04:40;NORMAL"
    ));

    static List<String> sessions_all_day = new ArrayList<>(List.of(
            "01.10.25 13:15;01.10.25 19:30;GOOD",
            "02.10.25 13:50;02.10.25 14:40;NORMAL",
            "03.10.25 13:50;03.10.25 14:40;NORMAL"
    ));

    @Test
    public void test_emptySessionsListShouldReturn0() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_empty);
        SleeplessSessionsCount func = new SleeplessSessionsCount();

        assertEquals(0L, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_normalSessionsListShouldReturn1() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_good);
        SleeplessSessionsCount func = new SleeplessSessionsCount();

        assertEquals(1L, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_longSleeplessSessionsListShouldReturn9() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_long_sleepless);
        SleeplessSessionsCount func = new SleeplessSessionsCount();

        assertEquals(9L, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_allNightSessionsListShouldReturn0() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_all_night);
        SleeplessSessionsCount func = new SleeplessSessionsCount();

        assertEquals(0L, func.apply(sessions).getResultValue());
    }

    @Test
    public void test_allDaySessionsListShouldReturn3() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_all_day);
        SleeplessSessionsCount func = new SleeplessSessionsCount();

        assertEquals(3L, func.apply(sessions).getResultValue());
    }
}
