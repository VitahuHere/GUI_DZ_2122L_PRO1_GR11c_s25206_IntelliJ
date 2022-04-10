package app;

import containers.classes.*;
import main.TimeOperations;
import port.Port;
import port.Train;
import sender.Sender;
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
                4. Load container on ship
                5. Unload container from ship
                6. Ships info
                7. Warehouse info
                8. Train info
                9. Senders info
                0. Exit
                """);
        ConsoleColors.printYellow("Type in number of the command you want to perform: ");

        int option = Evaluators.getIntFromInput(0, 9);

        switch (option) {
            case 0 -> System.exit(0);
            case 1 -> new Ship();
            case 2 -> createContainer();
            case 3 -> new Sender();
            case 4 -> ContainerLoading.listAndLoadContainer();
            case 5 -> containerUnloading();
            case 6 -> showShipInfo();
            case 7 -> showWarehouseInfo();
            case 8 -> showTrainInfo();
            case 9 -> showSendersInfo();
        }
        App.menu();
    }

    public static void showSendersInfo(){
        if(senders.size() == 0){
            System.out.println("There are no senders");
        }
        else{
            System.out.println("List of senders: ");
            senders.forEach(System.out::println);
        }
    }

    public static void showTrainInfo(){
        if (Port.train.getContainers().size() == 0){
            System.out.println("Train is empty");
        }
        else{
            Port.train.getContainers().forEach(System.out::println);
        }
    }

    public static void showWarehouseInfo(){
        System.out.println("Current date: " + TimeOperations.currentDate);
        System.out.println(Port.warehouse.getContainers().size() == 0 ? "Warehouse is empty" : Port.warehouse.getContainers());
    }

    public static void showShipInfo() {
        for (Ship ship : ships) {
            ConsoleColors.printBlue(ship.toString());
        }
        System.out.println();
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
