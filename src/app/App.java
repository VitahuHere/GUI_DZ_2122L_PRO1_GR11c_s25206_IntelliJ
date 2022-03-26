package app;

import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;

public class App{
    public App(){
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers, ships and ports.");
        menu();
    }

    private void menu() {
        ConsoleColors.printBlue("""
                    Type in number of command you want to perform
                    1. Create a cargo ship
                    2. Create a container
                    3. Load or unload containers
                    0. Exit
                    """);

        int option = Evaluators.getIntFromInput(0, 3);

        switch (option) {
            case 1 -> new Ship();
            case 2 -> new ContainerCreation();
            case 3 -> nothing();
        }
    }
    private void nothing(){}
}
