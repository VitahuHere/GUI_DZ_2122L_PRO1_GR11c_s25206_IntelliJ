package app;

import containers.classes.*;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;
import utils.Constants;
import utils.Evaluators;

import java.util.ArrayList;


public class App {
    public static ArrayList<Ship> ships = new ArrayList<>();
    public static ArrayList<StandardContainer> containers = new ArrayList<>();

    public static void menu() {
        ConsoleColors.printYellow("Type in number of command you want to perform");
        ConsoleColors.printBlue("""
                1. Create a cargo ship
                2. Create a container
                3. Load container on ship
                4. Unload container from ship
                5. Ships info
                6. Warehouse info
                0. Exit
                """);

        int option = Evaluators.getIntFromInput(0, 6);

        switch (option) {
            case 0 -> System.exit(0);
            case 1 -> ships.add(new Ship());
            case 2 -> createContainer();
            case 3 -> ContainerLoading.listAndLoadContainer();
            case 4 -> containerUnloading();
            case 5 -> showShipInfo();
            case 6 -> showWarehouseInfo();
        }
    }

    public static void showWarehouseInfo(){
        System.out.println(Port.warehouse.getContainers());
        App.menu();
    }

    public static void showShipInfo() {
        for (Ship ship : ships) {
            ConsoleColors.printBlue(ship.toString());
        }
        System.out.println();
        App.menu();
    }

    public static void createContainer() {
        int choice;
        ConsoleColors.printYellow("Welcome to Container creation page!");
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
        choice = Evaluators.getIntFromInput(0, 7);


        switch (choice) {
            case 1 -> new StandardContainer();
            case 2 -> new HeavyContainer();
            case 3 -> new ChillerContainer();
            case 4 -> new ExplosivesContainer();
            case 5 -> new LiquidsContainer();
            case 6 -> new ToxicLiquidContainer();
            case 7 -> new ToxicLooseMaterialContainer();
            case 0 -> App.menu();
        }
    }

    public static void containerUnloading() {
        ConsoleColors.printYellow("Welcome to Container unloading page!");
        ConsoleColors.printBlue("""
                Please choose where to unload container:
                1. Warehouse
                2. Transport Train
                0. Back
                """);
        int choice = Evaluators.getIntFromInput(0, 2);

        switch (choice) {
            case 0 -> App.menu();
            case 1 -> ContainerUnloading.unloadToWarehouse();
            case 2 -> ContainerUnloading.unloadToTrain();
        }
    }
}
