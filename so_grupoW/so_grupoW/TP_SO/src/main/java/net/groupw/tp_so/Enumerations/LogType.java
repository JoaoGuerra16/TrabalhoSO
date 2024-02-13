package net.groupw.tp_so.Enumerations;

public enum LogType {
    INFO("\u001B[37;40m"),      // White text on black background
    WARNING("\u001B[33;40m"),   // Yellow text on black background
    ERROR("\u001B[31;40m");     // Red text on black background

    private final String ansiCode;

    LogType(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return ansiCode;
    }
}
