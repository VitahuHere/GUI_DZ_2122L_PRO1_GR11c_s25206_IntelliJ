package app;

import containers.classes.StandardContainer;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;


public class App{
    public static ArrayList<Ship> ships;
    public static ArrayList<StandardContainer> containers;
    public static Port port;

    public App(){
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
        ships = new ArrayList<>();
        containers = new ArrayList<>();
        port = new Port("Amsterdam", 95);
        menu();
    }

    public static void menu() {
        ConsoleColors.printBlue("""
                    Type in number of command you want to perform
                    1. Create a cargo ship
                    2. Create a container
                    3. Load container
                    4. Unload container
                    0. Exit
                    """);

        int option = Evaluators.getIntFromInput(0, 3);

        switch (option) {
            case 0 -> System.exit(0);
            case 1 -> ships.add(new Ship());
            case 2 -> new ContainerCreation();
            case 3 -> new ContainerLoading();
            case 4 -> new ContainerUnloading();
        }


    }
}
