package log;

import java.io.Console;

public class IGLog {

    private static final Console CONSOLE = System.console();
    private static final String  YELLOW  = "\\033[1;33m";
    private static final String  RED     = "\\033[0;31m";
    private static final String  CLEAR   = "\\033[0m";

    private static String addColors(String color, String message) {
        String useColor;
        String useClear;

        if (CONSOLE == null) {
            useColor = "";
            useClear = "";
        } else {
            useColor = color;
            useClear = CLEAR;
        }

        return useColor + message + useClear;
    }

    public static void write(String message) {
        System.out.println(message);
    }

    public static void info(String message) {
        System.out.println(addColors(YELLOW, message));
    }

    public static void error(String message) {
        System.err.println(addColors(RED, message));
    }

}
