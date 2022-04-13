package main;

import containers.classes.*;
import port.Port;
import port.Train;
import port.Warehouse;
import sender.Sender;
import ship.ContainerLoading;
import ship.ContainerUnloading;
import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;


public class App {
    // storing all objects of an app for later injecting or saving
    public static ArrayList<Ship> ships = new ArrayList<>();
    public static ArrayList<StandardContainer> containers = new ArrayList<>();
    public static ArrayList<Train> trains = new ArrayList<>();
    public static ArrayList<Sender> senders = new ArrayList<>();

    public static void menu() {
        ConsoleColors.printBlue("""
                1. Create a cargo ship
                2. Create a container
                3. Create sender
                4. Manage containers
                5. Ship arrival
                6. Ship departure
                7. Information about ships
                8. Information about warehouse
                9. Information about trains
                10. Information about senders
                0. Exit
                """);
        ConsoleColors.printYellow("Type in number of the command you want to perform: ");

        int option = Evaluators.getIntFromInput(0, 11);

        switch (option) {
            case 0 -> System.exit(0);
            case 1 -> shipCreation();
            case 2 -> createContainer();
            case 3 -> new Sender();
            case 4 -> manageContainers();
            case 5 -> arriveShip();
            case 6 -> departShip();
            case 7 -> showShipInfo();
            case 8 -> showWarehouseInfo();
            case 9 -> showTrainInfo();
            case 10 -> showSendersInfo();
        }
        App.menu();
    }

    public static void shipCreation(){
        ConsoleColors.printYellow("Welcome to Ship creation page!");
        ConsoleColors.printYellow("Please enter following information:");
        new Ship();
    }

    public static void manageContainers(){
        System.out.println("""
                Welcome to container managing page
                Please select from the following:
                1. Load container onto a ship
                2. Offload container from a ship
                3. Remove container from warehouse
                """);
        int choice = Evaluators.getIntFromInput(1, 3);
        switch (choice){
            case 1 -> ContainerLoading.loadContainerOntoAShip();
            case 2 -> containerUnloading();
            case 3 -> manualContainerRemoval();
        }
    }

    private static void manualContainerRemoval(){

    }

    private static void listShip(ArrayList<Ship> ships){
        ships.forEach(ship -> System.out.println(
                "Ship id: " + ship.id +
                        " name: " + ship.name +
                        " home port: " + ship.homePort +
                        " departure port: " + ship.departurePort +
                        " arrival port: " + ship.arrivalPort)
        );
    }

    public static void arriveShip() {

    }

    public static void departShip() {

    }

    public static void showSendersInfo() {
        if (senders.size() == 0) {
            System.out.println("There are no senders");
        } else {
            System.out.println("List of senders: ");
            senders.forEach(System.out::println);
        }
    }

    public static void showTrainInfo() {
        if (Port.train.getContainers().size() == 0) {
            System.out.println("Train is empty");
        } else {
            Port.train.getContainers().forEach(System.out::println);
        }
    }

    public static void showWarehouseInfo() {
        System.out.println("Current date: " + TimeOperations.currentDate);
        if(Port.warehouse.getContainers().size() == 0){
            System.out.println("Warehouse is empty.");
        }
        else{
            System.out.println(Port.warehouse);
        }

    }

    public static void showShipInfo() {
        System.out.println("Ships sailing: ");
        if (ships.size() == 0) {
            ConsoleColors.printGreen("N/A");
        } else {
            for (Ship ship : ships) {
                ConsoleColors.printBlue(ship.toString());
            }
        }
        System.out.println("Ships in port: ");
        if (Port.ships.size() == 0) {
            ConsoleColors.printGreen("N/A");
        } else {
            for (Ship ship : Port.ships) {
                ConsoleColors.printBlue(ship.toString());
            }
        }
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
