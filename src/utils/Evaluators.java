package utils;

import java.util.*;

public class Evaluators {
    public static boolean getBooleanFromInput(String key) {
        System.out.println(key + ":");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y");
    }

    public static String getStringFromInput(String key) {
        System.out.println(key + ":");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static ArrayList<String> getArrayListFromInput(String key) {
        ConsoleColors.printGreen("Please enter " + key + " pressing enter after each one. Enter 'end' to finish adding or 'none' to set nothing.");
        ArrayList<String> returner = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();
        if (value.equals("none") || value.equals("None") || value.equals("NONE")) {
            return returner;
        }
        while (!value.equals("end")) {
            returner.add(value);
            value = scanner.next();
        }

        return returner;
    }

    public static int getIntFromInput(String key) {
        int choice;
        while (true) {
            try {
                System.out.println(key + ":");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice < 0) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                ConsoleColors.printRed("Invalid value, please enter again.");
            }
        }
        return choice;
    }

    public static int getIntFromInput(int min, int max) {
        int choice;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice < min || choice > max) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                ConsoleColors.printRed("Invalid value, please enter again.");
            }
        }
        return choice;
    }

    public static int getIntFromInput(int max) {
        int choice;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if (choice > max) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                ConsoleColors.printRed("Invalid value, please enter again.");
            }
        }
        return choice;
    }

    public static String getPeselFromInput(String key) {
        System.out.println(key + ":");
        String pesel;
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                pesel = scanner.next();
                if (pesel.length() != 11) {
                    ConsoleColors.printRed("PESEL must be 11 digits long, please enter again.");
                    continue;
                }
                if(!validatepesel(pesel)) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                ConsoleColors.printRed("Invalid PESEL, please enter again.");
            }
        }
        return pesel;
    }

    public static boolean validatepesel(String pesel) {
        if(pesel.length() != 11) {
            return false;
        }
        int[] peselArray = new int[11];
        for (int i = 0; i < 11; i++) {
            peselArray[i] = Character.getNumericValue(pesel.charAt(i));
        }

        int sum = peselArray[0] +
                3 * peselArray[1] +
                7 * peselArray[2] +
                9 * peselArray[3] +
                peselArray[4] +
                3 * peselArray[5] +
                7 * peselArray[6] +
                9 * peselArray[7] +
                peselArray[8] +
                3 * peselArray[9];
        sum %= 10;
        sum = 10 - sum;
        sum %= 10;
        return sum == peselArray[10];
    }
}
