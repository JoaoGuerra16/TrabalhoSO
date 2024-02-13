package net.groupw.tp_so.Files;

import net.groupw.tp_so.Interface.LogCallback;

import java.util.ArrayList;
import java.util.List;

public class LogEntry {
    private static final LogEntry INSTANCE = new LogEntry();
    private List<Log> logList = new ArrayList<>();
    private LogCallback logCallback;

    private LogEntry() {
    }

    public static LogEntry getInstance() {
        return INSTANCE;
    }

    public void addLog(Log log) {
        logList.add(log);
        if (logCallback != null) {
            logCallback.onLogAdded(log.toString());
        }
    }

    public ArrayList<Log> getLogList() {
        return (ArrayList<Log>) logList;
    }

    public void setLogCallback(LogCallback logCallback) {
        this.logCallback = logCallback;
    }
}