package me.cadox8.commons;

import lombok.Getter;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = -5451545454L;

    @Getter private final String action; //Without /
    @Getter private final Object[] args;

    public Message(String action, Object... args) {
        this.action = action;
        this.args = args;
    }

    public String argsToString(int arg) {
        return String.valueOf(args[arg]);
    }
    public int argstoInt(int arg) {
        return Integer.valueOf(argsToString(arg));
    }

    public String buildMsg() {
        return "/" + action + buildArgs() + "\r\n";
    }

    private String buildArgs() {
        if (args == null || args.length == 0) return "";
        String result = " ";

        for (Object o : args) result += String.valueOf(o) + " ";
        return result;
    }
}
