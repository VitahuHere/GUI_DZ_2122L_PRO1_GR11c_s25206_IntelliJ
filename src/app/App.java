package app;

import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;


public class App{
    public App(){
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers na ships.");
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
            case 1 -> new Ship();
            case 2 -> new ContainerCreation();
        }
    }
}
