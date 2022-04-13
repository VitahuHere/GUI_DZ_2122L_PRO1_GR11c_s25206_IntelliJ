package ship;

import main.App;
import containers.classes.StandardContainer;
import port.Port;
import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

public class ContainerLoading {

    public static void loadContainerOntoAShip() {
        ConsoleColors.printYellow("Welcome to the container loading page.");
        if(listContainers()){
            ConsoleColors.printYellow("Please select a container");
            int id = Evaluators.getIntFromInput("Container id");
            StandardContainer container = containerLookUp(id);

            if(container != null){
                ConsoleColors.printYellow("Please select a ship: ");

                if(listAvailableShips()) {
                    id = Evaluators.getIntFromInput("Ship id");
                    Ship ship = shipLookUp(id);

                    if (ship != null) {

                        if (checkList(ship, container)) {
                            loadContainer(ship, container);
                        }
                    }
                }
            }
        }
        App.menu();
    }

    private static boolean listContainers() {
        boolean isNotEmpty = false;
        ConsoleColors.printYellow("Free available containers:");
        if (App.containers.size() > 0) {
            isNotEmpty = true;
            for (StandardContainer container : App.containers) {
                System.out.println(
                        "Container type: " + container.getClass().getSimpleName() +
                                "\nid " + container.id +
                                ": \n" + "size: " + container.size +
                                "\n" + "total weight: " + container.totalWeight + " kg \n"
                );
            }
        } else {
            ConsoleColors.printGreen("N/A");
        }

        ConsoleColors.printYellow("Free available containers in warehouse:");
        if (Port.warehouse.getContainers().size() > 0) {
            isNotEmpty = true;
            for (StandardContainer container : Port.warehouse.getContainers()) {
                System.out.println(
                        "Container id " + container.id +
                                "\ntype: " + container.getClass().getSimpleName() +
                                "\nsize: " + container.size +
                                "\ntotal weight: " + container.totalWeight + " kg \n"
                );
            }
        } else {
            ConsoleColors.printGreen("N/A");
        }
        return isNotEmpty;
    }

    private static void loadContainer(Ship ship, StandardContainer container) {
        ship.addContainerOfType(container);
        App.containers.remove(container);
        ConsoleColors.printGreen("Successfully loaded container.");
    }

    private static boolean checkList(Ship ship, StandardContainer container){
        if(ship.getSlotsAvailable() <= 0 || ship.getWeightAvailable() + container.totalWeight > ship.maxCargoWeight){
            ConsoleColors.printRed("Maximum container count or mass reached. Cannot add another one.");
            return false;
        }
        if(container.sender == null){
            ConsoleColors.printRed("Container doesn't have sender. Please assign sender before loading");
            setSender(container);
        }
        return true;
    }

    private static void setSender(StandardContainer container) {
        if (App.senders.size() == 0) {
            ConsoleColors.printRed("No senders available. Please create new sender to be able to load container");
        } else {
            ConsoleColors.printYellow("Please select sender:");
            for (int i = 1; i <= App.senders.size(); i++) {
                Sender sender = App.senders.get(i - 1);
                ConsoleColors.printBlue(i + ") " + sender.name + " " + sender.surname + " " + sender.getBirthday());
            }
            int senderIndex = Evaluators.getIntFromInput(App.senders.size());
            container.sender = App.senders.get(senderIndex-1);
        }
    }

    private static StandardContainer containerLookUp(int choice) {
        while(true){
            if(choice == -1){
                return null;
            }
            for (StandardContainer c : App.containers) {
                if (c.id == choice) {
                    return c;
                }
            }
            for (StandardContainer c : Port.warehouse.getContainers()) {
                if (c.id == choice) {
                    return c;
                }
            }
            ConsoleColors.printRed("Container with id " + choice + " does not exist or is not available. " +
                    "Please re-enter or type -1 to exit:");
            choice = Evaluators.getIntFromInput("Container id");
        }
    }

    private static Ship shipLookUp(int choice){
        return searchForShip(choice);
    }

    private static boolean listAvailableShips() {
        if (Port.ships.size() > 0) {
            ConsoleColors.printYellow("Available ships in port:");
            for (Ship ship : Port.ships) {
                System.out.println(
                        "Ship id " + ship.id +
                                ": \n" + "Ship name: " + ship.name +
                                "\n" + ship.getSlotsAvailable() + " slots available" +
                                "\n" + "payload weight available: " + ship.getWeightAvailable() + " kg" +
                                "\nFrom: " + (ship.departurePort == null ? "n/a" : ship.departurePort) +
                                "\n" + "To: " + (ship.arrivalPort == null ? "n/a" : ship.arrivalPort) + "\n"
                );
            }
        } else {
            ConsoleColors.printRed("No ships available.");
        }
        return Port.ships.size() > 0;
    }

    private static Ship searchForShip(int choice) {
        while(true){
            if(choice == -1){
                return null;
            }
            for (Ship ship : Port.ships) {
                if (ship.id == choice) {
                    return ship;
                }
            }
            ConsoleColors.printRed("Ship with id " + choice + " does not exist or is not available. Please re-enter or type -1 to exit:");
            choice = Evaluators.getIntFromInput("Ship id");
        }
    }
}

