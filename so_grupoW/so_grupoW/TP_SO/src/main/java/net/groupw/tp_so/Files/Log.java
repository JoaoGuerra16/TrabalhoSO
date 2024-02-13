package net.groupw.tp_so.Files;


import net.groupw.tp_so.Enumerations.LogType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Log {
    private LogType logType;
    private String logMessages;
    private LocalDateTime timestamp;


public Log(LogType logType, String logMessages, LocalDateTime timestamp) {
        this.logType = logType;
        this.logMessages = logMessages;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM HH:mm:ss");
        String formattedTimestamp = timestamp.format(formatter);

        return  "[" + logType + "]" +
                "[" + formattedTimestamp + "]" +
                logMessages;
    }

    public String getTimestamp() {
        return String.valueOf(timestamp);
    }

    public String getLogMessages() {
        return logMessages;
    }
}
