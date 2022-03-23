package app;

import utils.ConsoleColors;

public class App{
    public App(){
        ConsoleColors.printGreen("Welcome to CargoApp. Here you will manage your containers, ships and ports.");
        ObjectCreation oc = new ObjectCreation();
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

    private void loadContainer(){

    }

    private void unloadContainer(){

    }

    private void exit(){

    }
}
