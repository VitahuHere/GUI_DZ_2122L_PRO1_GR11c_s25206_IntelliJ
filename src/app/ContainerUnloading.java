package app;

import containers.classes.StandardContainer;
import port.Port;
import ship.Ship;
import utils.ConsoleColors;
import utils.Evaluators;

public class ContainerUnloading {
    public static boolean listAvailableContainers(){
        if(Port.ships.size() == 0){
            ConsoleColors.printRed("There are no ships in the port.");
            return false;
        }
        for(Ship ship : Port.ships){
            ConsoleColors.printGreen(
                    "Ship id: " + ship.id +
                    "\nShip name: " + ship.name +
                    "\nContainers on board: {"
            );
            for(StandardContainer container : ship.listOfContainers){
                System.out.println(
                        "Container id: " + container.id +
                        "\ntype: " + container.getClass().getSimpleName() +
                        "\nsize: " + container.size +
                        "\nweight: " + container.totalWeight
                );
            }
            ConsoleColors.printGreen("}");
        }
        return true;
    }

    private static boolean containerLookUp(int id, String key){
        for(Ship ship : Port.ships){
            for(StandardContainer container : ship.listOfContainers){
                if(container.id == id){
                    if(key.equals("warehouse")){
                        ship.offloadContainerToWarehouse(container);
                    }
                    else if(key.equals("train")){
                        ship.offloadOntoTrain(container);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public static void unloadToWarehouse(){
        ConsoleColors.printYellow("Welcome to warehouse container unloading page.");
        if(listAvailableContainers()){
            ConsoleColors.printYellow("Please enter the container id you want to unload to warehouse.");
            int choice = Evaluators.getIntFromInput("Container id");
            if(containerLookUp(choice, "warehouse")){
                ConsoleColors.printGreen("Container unloading successful.");
            }
        }
        App.menu();
    }

    public static void unloadToTrain(){
        ConsoleColors.printYellow("Welcome to train container unloading page.");
        if(listAvailableContainers()){
            ConsoleColors.printYellow("Please enter the container id you want to unload onto a train.");
            int choice = Evaluators.getIntFromInput("Container id");
            if(containerLookUp(choice, "train")){
                ConsoleColors.printGreen("Container unloading successful.");
            }
        }
    }
}
