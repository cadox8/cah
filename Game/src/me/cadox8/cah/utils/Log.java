package me.cadox8.cah.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class Log {

    public static void debugLog(String msg) {
        log(LogType.DEBUG, msg);
    }

    public static void errorLog(String msg) {
        log(LogType.ERROR, msg);
    }

    public static void infoLog(String msg) {
        log(LogType.INFO, msg);
    }

    public static void log(LogType type, String msg) {
        System.out.println(type.getPrefix() + " " + msg);
    }

    @AllArgsConstructor
    public enum LogType {

        NORMAL(""),
        ERROR("[ERROR]"),
        DEBUG("[DEBUG]"),
        SUCCESS("[SUCCESS]"),
        INFO("[INFO]");

        @Getter private String prefix;
    }
}
