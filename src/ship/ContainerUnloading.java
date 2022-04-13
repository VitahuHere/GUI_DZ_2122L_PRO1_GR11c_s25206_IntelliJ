package ship;

import main.App;
import containers.classes.StandardContainer;
import port.Port;
import utils.ConsoleColors;
import utils.Evaluators;

public class ContainerUnloading {
    private static StandardContainer container;
    private static Ship ship;

    public static void unloadToWarehouse() {
        ConsoleColors.printYellow("Welcome to warehouse container unloading page.");
        if (listAvailableContainers()) {
            container = containerLookUp();
            if(container != null){
                if(checkForStrikes(container)){
                    Port.warehouse.addContainer(container);
                    ship.removeContainerOfType(container);
                    ConsoleColors.printGreen("Successfully offloaded container to warehouse");
                }
            }
        }
        App.menu();
    }

    public static void unloadToTrain() {
        ConsoleColors.printYellow("Welcome to train container unloading page.");
        if (listAvailableContainers()) {
            container = containerLookUp();
            if(container != null){
                Port.train.addContainer(container);
            }
        }
        App.menu();
    }

    public static boolean listAvailableContainers() {
        if (Port.ships.size() == 0) {
            ConsoleColors.printRed("There are no ships in the port.");
            return false;
        }

        for (Ship ship : Port.ships) {
            ConsoleColors.printGreen(
                    "Ship id: " + ship.id +
                            "\nShip name: " + ship.name +
                            "\nContainers on board: {"
            );
            for (StandardContainer container : ship.containers) {
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

    private static StandardContainer containerLookUp() {
        System.out.println("Please enter container id: ");
        int id;
        while(true){
            id = Evaluators.getIntFromInput();
            if(id == -1){
                return null;
            }
            for (Ship s : Port.ships) {
                for (StandardContainer cont : s.containers) {
                    if(cont.id == id){
                        container = cont;
                        ship = s;
                        return cont;
                    }
                }
            }
            System.out.println("Container id is invalid or container doesn't exist. Please re-enter or enter -1 to exit");
        }
    }

    private static boolean checkForStrikes(StandardContainer container){
        return container.sender.strikes < 2;
    }
}
