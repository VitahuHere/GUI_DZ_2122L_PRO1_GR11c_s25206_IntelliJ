package main;

import utils.ConsoleColors;

import java.util.*;

public class App {
    public App(){
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers, ships and ports.");
    }

    private void menu(){
        ConsoleColors.printGreen("""
            Type in command you want to perform
            1. Create a cargo ship
            2. Create a container
            3. Load or unload containers
            'exit' To end the program.
        """);
    }

    private void createShip(){
        Map<String, String> strings = new LinkedHashMap<>(){{
            put("Name", "");
            put("Home Port", "");
        }};
        Map<String, Integer> integerMap = new LinkedHashMap<>(){{
            put("Maximum Container Count", 0);
            put("Maximum Payload Weight", 0);
            put("Maximum Toxic/Explosive Containers Count", 0);
            put("Maximum Heavy Containers Count", 0);
            put("Maximum Electric Container Count", 0);
        }};
        ConsoleColors.printBlue("Welcome to Ship Creation Page. Please enter following information:");

        strings.forEach((key, value) -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println(key + ":");
            strings.put(key, scanner.next());
        });

        integerMap.forEach((key, value) -> {
            System.out.println(key + ":");
            int temp;
            while(true){
                try{
                    Scanner scanner = new Scanner(System.in);
                    temp = scanner.nextInt();
                    integerMap.put(key, temp);
                    break;
                }
                catch (InputMismatchException e){
                    ConsoleColors.printRed("Please only input integers");
                }
            }
        });
    }

    private void createContainer(){

    }

    private void loadContainer(){

    }

    private void unloadContainer(){

    }

    private void exit(){

    }
}
