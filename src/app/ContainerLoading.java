package app;

import containers.classes.StandardContainer;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;

public class ContainerLoading {
    public static void listAndLoadContainer() {
        ConsoleColors.printYellow("Welcome to the container loading page.");
        boolean isEmpty = true;
        if(App.containers.size() > 0 || Port.warehouse.getContainers().size() > 0) {
            isEmpty = false;
            ConsoleColors.printYellow("Free available containers:");
            if(App.containers.size() > 0) {
                for(StandardContainer container : App.containers) {
                    System.out.println(
                            "Container type: " + container.getClass().getSimpleName() +
                                    "\nid " + container.id +
                                    ": \n" + "size: " + container.size +
                                    "\n" + "total weight: " + container.totalWeight + " kg \n"
                    );
                }
            }
            else{
                ConsoleColors.printGreen("N/A");
            }
            ConsoleColors.printYellow("Free available containers in warehouse:");
            if(Port.warehouse.getContainers().size() > 0) {
                for(StandardContainer container : Port.warehouse.getContainers()) {
                    System.out.println(
                            "Container id " + container.id +
                            "\ntype: " + container.getClass().getSimpleName() +
                            "\nsize: " + container.size +
                            "\ntotal weight: " + container.totalWeight + " kg \n"
                    );
                }
            }
            else{
                ConsoleColors.printGreen("N/A");
            }
        }
        else{
            ConsoleColors.printRed("No containers available.");
        }
        if(!isEmpty) {
            ConsoleColors.printYellow("Please select one to load onto the ship: ");
            int id = Evaluators.getIntFromInput("Container id");
            StandardContainer container = containerLookUp(id);
            while(container == null){
                id = Evaluators.getIntFromInput("Container id");
                container = containerLookUp(id);
            }
            Ship ship = listAvailableShips();
            while(ship == null){
                ship = listAvailableShips();
            }
            ship.loadContainer(container);
            App.containers.remove(container);
            ConsoleColors.printGreen("Successfully loaded container.");
            ConsoleColors.printBlue("Returning to main menu.");
        }
        App.menu();
    }

    private static StandardContainer containerLookUp(int choice){
        for(StandardContainer container : App.containers) {
            if(container.id == choice) {
                return container;
            }
        }
        for(StandardContainer container : Port.warehouse.getContainers()) {
            if(container.id == choice) {
                return container;
            }
        }
        ConsoleColors.printRed("Container with id " + choice + " does not exist or is not available.");
        return null;
    }

    private static Ship listAvailableShips() {
        if(Port.ships.size() > 0) {
            ConsoleColors.printYellow("Available ships in port:");
            for(Ship ship : Port.ships) {
                System.out.println(
                        "Ship id " + ship.id +
                        ": \n" + "Ship name: " + ship.name +
                        "\n" + ship.getSlotsAvailable() + " slots available" +
                        "\n" + "payload weight available: " + ship.getWeightAvailable() + " kg" +
                        "\nFrom: " + (ship.departurePort == null ? "n/a" : ship.departurePort) +
                        "\n" + "To: " + (ship.arrivalPort == null ? "n/a" : ship.arrivalPort) + "\n"
                );
            }
            ConsoleColors.printYellow("Please select one to load onto: ");
            return searchForShip();
        }
        else{
            ConsoleColors.printRed("No ships available.");
        }
        return null;
    }

    private static Ship searchForShip(){
        int choice = Evaluators.getIntFromInput("Ship id");
        for(Ship ship : Port.ships) {
            if(ship.id == choice) {
                return ship;
            }
        }
        ConsoleColors.printRed("Ship with id " + choice + " does not exist or is not available.");
        return null;
    }
}

