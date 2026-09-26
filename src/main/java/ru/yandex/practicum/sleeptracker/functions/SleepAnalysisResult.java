package ru.yandex.practicum.sleeptracker.functions;

public class SleepAnalysisResult {
    private String resultMessage;
    private Object resultValue;

    public SleepAnalysisResult(String resultMessage, Object resultValue) {
        this.resultMessage = resultMessage;
        this.resultValue = resultValue;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public Object getResultValue() {
        return resultValue;
    }
}
