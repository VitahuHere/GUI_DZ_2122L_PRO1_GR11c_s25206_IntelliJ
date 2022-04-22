package main;

import containers.classes.*;
import port.Port;
import port.Train;
import sender.Sender;
import ship.ContainerLoading;
import ship.ContainerUnloading;
import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;
import utils.caching.CacheHandler;

import java.util.ArrayList;


public class App {
    // storing all objects of an app for later injecting or saving
    public static ArrayList<Ship> ships = new ArrayList<>();
    public static ArrayList<StandardContainer> containers = new ArrayList<>();
    public static ArrayList<Train> trains = new ArrayList<>();
    public static ArrayList<Sender> senders = new ArrayList<>();
    public static ArrayList<Integer> removedIds = new ArrayList<>();

    public static void menu() {
        ConsoleColors.printYellow("Main menu:");
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
                11. Save current port status
                0. Exit
                """);
        ConsoleColors.printYellow("Type in number of the command you want to perform: ");

        int option = Evaluators.getIntFromInput(0, 11);

        switch (option) {
            case 0 -> exit();
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
            case 11 -> save();
        }
        App.menu();
    }

    private static void save() {
        CacheHandler.saveApp();
        ConsoleColors.printGreen("Saved!");
    }

    private static void exit() {
        CacheHandler.saveApp();
        System.exit(0);
    }

    public static void shipCreation() {
        ConsoleColors.printYellow("Welcome to Ship creation page!");
        ConsoleColors.printYellow("Please enter following information:");
        new Ship();
    }

    public static void manageContainers() {
        ConsoleColors.printYellow("Welcome to container managing page");
        ConsoleColors.printBlue("""
                Please select from the following:
                1. Load container onto a ship
                2. Offload container from a ship
                3. Remove container from warehouse
                4. Move free container to warehouse
                0. Back
                """);
        int choice = Evaluators.getIntFromInput(0, 4);
        switch (choice) {
            case 1 -> ContainerLoading.loadContainerOntoAShip();
            case 2 -> containerUnloading();
            case 3 -> manualContainerRemoval();
            case 4 -> ContainerLoading.loadContainerToWarehouse();
        }
    }

    private static ArrayList<String> listContainers() {
        ArrayList<String> lc = new ArrayList<>();
        for (StandardContainer container : Port.warehouse.getContainers()) {
            lc.add("id: " + container.id +
                    ", arrival date: " + container.arrivalDate +
                    ", due date: " + container.dueDate);
        }
        return lc;
    }

    private static void manualContainerRemoval() {
        if (Port.warehouse.getContainers().size() == 0) {
            System.out.println("No containers available");
        } else {
            ConsoleColors.printYellow("Select container to be removed:");
            for (int i = 1; i <= Port.warehouse.getContainers().size(); i++) {
                System.out.println(i + ") " + listContainers().get(i - 1));
            }
            int choice = Evaluators.getIntFromInput(1, Port.warehouse.getContainers().size());
            Port.warehouse.removeContainer(Port.warehouse.getContainers().get(choice - 1));
            ConsoleColors.printGreen("Successfully removed");
        }
    }

    private static ArrayList<String> listShip(ArrayList<Ship> ships) {
        ArrayList<String> s = new ArrayList<>();
        ships.forEach(ship -> s.add(
                "Ship id: " + ship.id +
                        " name: " + ship.name +
                        " home port: " + ship.homePort +
                        " departure port: " + ship.departurePort +
                        " arrival port: " + ship.arrivalPort)
        );
        return s;
    }

    public static void arriveShip() {
        if (App.ships.size() == 0) {
            System.out.println("No ships available");
        } else {
            ConsoleColors.printYellow("Select a ship to dock: ");
            for (int i = 1; i <= App.ships.size(); i++) {
                System.out.println(i + ") " + listShip(App.ships).get(i - 1));
            }
            int choice = Evaluators.getIntFromInput(1, App.ships.size());
            Port.ships.add(App.ships.get(choice - 1));
            App.ships.get(choice - 1).arrivalPort = Port.name;
            App.ships.remove(App.ships.get(choice - 1));
            ConsoleColors.printGreen("Ship arrived");
        }
    }

    public static void departShip() {
        if (Port.ships.size() == 0) {
            System.out.println("No ships available");
        } else {
            ConsoleColors.printYellow("Select a ship to depart: ");
            for (int i = 1; i <= Port.ships.size(); i++) {
                System.out.println(i + ") " + listShip(Port.ships).get(i - 1));
            }
            int choice = Evaluators.getIntFromInput(1, Port.ships.size());
            App.ships.add(Port.ships.get(choice - 1));
            Port.ships.get(choice - 1).departurePort = Port.name;
            Port.ships.remove(Port.ships.get(choice - 1));
            ConsoleColors.printGreen("Ship departed");
        }
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
            System.out.println(Port.train);
        }
    }

    public static void showWarehouseInfo() {
        System.out.println("Current date: " + TimeOperations.currentDate);
        if (Port.warehouse.getContainers().size() == 0) {
            System.out.println("Warehouse is empty.");
        } else {
            System.out.println(Port.warehouse);
        }

    }

    public static void showShipInfo() {
        System.out.println("Ships sailing: ");
        if (ships.size() == 0) {
            ConsoleColors.printGreen("N/A");
        } else {
            for (Ship ship : ships) {
                System.out.println(ship.toString());
            }
        }
        System.out.println("\nShips in port: ");
        if (Port.ships.size() == 0) {
            ConsoleColors.printGreen("N/A");
        } else {
            for (Ship ship : Port.ships) {
                System.out.println(ship.toString() + "\n");
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
