package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.exceptions.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SleepingLogLoaderTest {

    @Test
    public void test_emptyFileShouldReturnEmptyList() throws IOException {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions("src/main/resources/empty_log.txt");

        assertEquals(0, sessions.size());
    }

    @Test
    public void test_13RowsFileShouldReturn13ElementsList() throws IOException {
        ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions("src/main/resources/sleep_log.txt");

        assertEquals(13, sessions.size());
    }

    @Test
    public void test_badFormatFileShouldRiseSleepingQualityFormatException() throws IOException {
        boolean exRised = false;
        try {
            ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions("src/main/resources/bad_quality_log.txt");
        } catch (SleepingQualityFormatException e) {
            exRised = true;
        }

        assertTrue(exRised);
    }

    @Test
    public void test_badDateTimeFormatFileShouldRiseDateTimeParseException() throws IOException {
        boolean exRised = false;
        try {
            ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions("src/main/resources/bad_date_time_log.txt");
        } catch (DateTimeParseException e) {
            exRised = true;
        }

        assertTrue(exRised);
    }

    @Test
    public void test_badDateTimeChainFileShouldRiseDateTimeParseChainException() throws IOException {
        boolean exRised = false;
        try {
            ArrayList<SleepingSession> sessions = SleepingLogLoader.loadSessions("src/main/resources/bad_date_time_chain_log.txt");
        } catch (DateTimeParseChainException e) {
            exRised = true;
        }

        assertTrue(exRised);
    }
}
