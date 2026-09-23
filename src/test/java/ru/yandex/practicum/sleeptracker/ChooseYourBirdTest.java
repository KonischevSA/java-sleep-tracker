package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.functions.ChooseYourBird;
import ru.yandex.practicum.sleeptracker.functions.SleepingSessionsBadQualityCount;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChooseYourBirdTest {

    static List<String> sessions_empty = new ArrayList<>();

    static List<String> sessions_good = new ArrayList<>(List.of(
            "01.10.25 23:59;02.10.25 00:00;GOOD",
            "02.10.25 22:00;03.10.25 09:00;BAD",
            "03.10.25 14:10;03.10.25 15:00;NORMAL",
            "03.10.25 23:00;04.10.25 09:00;BAD",
            "05.10.25 00:10;05.10.25 06:20;GOOD"
    ));

    static List<String> sessions_single_23_9 = new ArrayList<>(List.of(
            "02.10.25 23:00;03.10.25 09:00;NORMAL"
    ));

    static List<String> sessions_single_after_23_9 = new ArrayList<>(List.of(
            "02.10.25 23:01;03.10.25 09:01;NORMAL"
    ));

    static List<String> sessions_single_22_7 = new ArrayList<>(List.of(
            "02.10.25 22:00;03.10.25 07:00;BAD"
    ));

    static List<String> sessions_single_before_22_7 = new ArrayList<>(List.of(
            "02.10.25 22:00;03.10.25 07:00;BAD"
    ));

    static List<String> sessions_single_between_22_23 = new ArrayList<>(List.of(
            "02.10.25 22:30;03.10.25 07:00;BAD"
    ));

    static List<String> sessions_single_between_7_9 = new ArrayList<>(List.of(
            "02.10.25 22:00;03.10.25 07:30;BAD"
    ));

    static List<String> sessions_single_between_22_23_7_9 = new ArrayList<>(List.of(
            "02.10.25 22:30;03.10.25 07:30;BAD"
    ));

    @Test
    public void test_emptySessionsListShouldReturnPigeon() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_empty);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Голубь", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_after23AndAfter9HoursShouldReturnOwl() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_after_23_9);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Сова", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_23And9HoursShouldReturnOwl() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_23_9);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Сова", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_before22AndBefore7HoursShouldReturnSkylark() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_before_22_7);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Жаворонок", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_22And7HoursShouldReturnSkylark() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_22_7);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Жаворонок", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_between22And23HoursShouldReturnPigeon() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_between_22_23);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Голубь", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_between7And9HoursShouldReturnPigeon() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_between_7_9);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Голубь", func.apply(sessions).getResultValue());
    }

    @Test
    public void test_between22And23AndBetween7And9HoursShouldReturnPigeon() {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions(sessions_single_between_22_23_7_9);
        ChooseYourBird func = new ChooseYourBird();

        assertEquals("Голубь", func.apply(sessions).getResultValue());
    }
}
