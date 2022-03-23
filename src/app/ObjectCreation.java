package app;

import utils.ConsoleColors;

import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ObjectCreation {

    protected void createShip(){
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
        int choice;
        ConsoleColors.printBlue("""
                Please select container type:
                1. Standard container
                2. Heavy container
                3. Chiller container
                4. Container for explosives
                5. Container for liquids
                6. Toxic liquids container
                7. Toxic loose material container
                0. Back
                """);
        while(true){
            try{
                Scanner scanner = new Scanner(System.in);
                choice = scanner.nextInt();
                if(choice < 0 || choice > 7){
                    throw new InputMismatchException();
                }
                break;
            }
            catch (InputMismatchException e){
                ConsoleColors.printRed("Invalid option. Please enter the correct option.");
            }
        }

        switch(choice){
            case 1 -> createStandardContainer();
            case 2 -> creatHeavyContainer();
            case 3 -> createChillerContainer();
            case 4 -> createExplosiveContainer();
            case 5 -> createLiquidsContainer();
            case 6 -> createToxicLiquidContainer();
            case 7 -> createToxicLooseContainer();
            case 0 -> exit();
        }
    }
    private void createToxicLooseContainer(){

    }

    private void createToxicLiquidContainer(){

    }

    private void createLiquidsContainer(){

    }

    private void createExplosiveContainer(){

    }

    private void createChillerContainer(){

    }

    private void createStandardContainer(){

    }

    private void creatHeavyContainer(){

    }

    private void exit(){

    }
}


