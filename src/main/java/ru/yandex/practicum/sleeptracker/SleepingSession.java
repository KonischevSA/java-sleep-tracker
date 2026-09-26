package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private LocalDateTime sessionBeginning;
    private LocalDateTime sessionEnd;
    private SleepingSessionQuality sleepingQuality;
    private Duration sessionDuration;

    public SleepingSession(LocalDateTime sessionBeginning, LocalDateTime sessionEnd, SleepingSessionQuality sleepQuality) {
        this.sessionBeginning = sessionBeginning;
        this.sessionEnd = sessionEnd;
        this.sleepingQuality = sleepQuality;
        this.sessionDuration = Duration.between(sessionBeginning, sessionEnd);
    }

    public LocalDateTime getSessionBeginning() {
        return sessionBeginning;
    }

    public LocalDateTime getSessionEnd() {
        return sessionEnd;
    }

    public SleepingSessionQuality getSleepingQuality() {
        return sleepingQuality;
    }

    public Duration getSessionDuration() {
        return sessionDuration;
    }

    public boolean isNightSession() {
        LocalDateTime nightStart = LocalDateTime.of(sessionBeginning.toLocalDate(), LocalTime.of(0, 0));
        LocalDateTime nightEnd = LocalDateTime.of(sessionEnd.toLocalDate(), LocalTime.of(6, 0));

        return sessionBeginning.isBefore(nightEnd) && sessionEnd.isAfter(nightStart);
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "sessionBeginning=" + sessionBeginning.format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")) +
                ", sessionEnd=" + sessionEnd.format(DateTimeFormatter.ofPattern("dd.MM.yy HH:mm")) +
                ", sleepingQuality=" + sleepingQuality +
                ", sessionDuration (minutes)=" + sessionDuration.toMinutes() +
                '}';
    }
}
