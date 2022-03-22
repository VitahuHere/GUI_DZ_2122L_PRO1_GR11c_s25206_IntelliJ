package utils;
// part copied from https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println user: shakram02

public class ConsoleColors {
    public static final String RESET = "\033[0m";

    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String YELLOW = "\033[0;33m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    public static void printRed(String message){
        System.out.print(RED + "Warning: " + message + RESET);
    }

    public static void printGreen(String message){
        System.out.print(GREEN + message + RESET);
    }

    public static void printBlue(String message){
        System.out.print(BLUE + message + RESET);
    }
}
