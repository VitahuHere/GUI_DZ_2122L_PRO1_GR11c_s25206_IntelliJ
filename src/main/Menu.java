package main;

public class Menu {
    public Menu(){
        System.out.println(
                """
                Welcome to CargoApp. Here you will manage your containers, ships and ports.
                Type in command you want to perform
                1. Create a cargo ship
                2. Create a container
                3. Load or unload containers
                """
        );
    }
}
