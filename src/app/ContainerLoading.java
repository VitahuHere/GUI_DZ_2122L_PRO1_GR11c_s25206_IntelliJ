package app;

import containers.classes.StandardContainer;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;

public class ContainerLoading {
    public ContainerLoading() {

    }

    private void listAvailableContainers() {
        System.out.println("Available containers:");
        System.out.println(App.containers);
        System.out.println(Port.warehouse.getContainers());
        ConsoleColors.printYellow("Please select one to load onto the ship: ");
    }

    private void searchForContainer(){
        int choice = Evaluators.getIntFromInput("Container id");
        boolean exists = false;
        for(StandardContainer container : App.containers) {
            if(container.id == choice) {
                exists = true;
                break;
            }
        }
        if(!exists) {
            for(StandardContainer container : Port.warehouse.getContainers()) {
                if(container.id == choice) {
                    exists = true;
                    break;
                }
            }
        }
        if(!exists) {
            ConsoleColors.printRed("Container with id " + choice + " does not exist or is not available.");
        }
    }

    private void listAvailableShips() {
        System.out.println("Available ships:");
        System.out.println(App.ships);
        ConsoleColors.printYellow("Please select one to load onto: ");
    }

    private void searchForShip(){
        int choice = Evaluators.getIntFromInput("Ship id");
        boolean exists = false;
        for(Ship ship : App.ships) {
            if(ship.id == choice) {
                exists = true;
                break;
            }
        }
        if(!exists) {
            ConsoleColors.printRed("Ship with id " + choice + " does not exist or is not available.");
        }
    }
}

