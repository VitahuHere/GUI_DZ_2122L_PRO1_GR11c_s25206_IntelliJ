package utils;

import java.util.*;

public class Evaluators {
    public static boolean getBooleanFromInput(String key){
        System.out.println(key + ":");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        return input.equals("yes") || input.equals("Yes") || input.equals("y") || input.equals("Y");
    }

    public static String getStringInput(String key){
        System.out.println(key + ":");
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public static ArrayList<String> getArrayListFromInput(String key){
        ConsoleColors.printGreen("Please enter " + key + " pressing enter after each one. Enter 'end' to finish adding.");
        ArrayList<String> returner = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        String value = scanner.next();

        while (!value.equals("end")){
            returner.add(value);
            value = scanner.next();
        }

        return returner;
    }

    public static int getIntFromInput(String key){
        int choice;
        while(true){
            try{
                System.out.println(key + ":");
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if(choice < 0){
                    throw new InputMismatchException();
                }
                break;
            }
            catch (InputMismatchException e){
                ConsoleColors.printRed("Invalid value, please enter again.");
            }
        }
        return choice;
    }


}
