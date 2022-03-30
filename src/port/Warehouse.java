package port;

import containers.classes.StandardContainer;
import utils.ConsoleColors;

import java.util.ArrayList;

public class Warehouse {
    public final int maxCapacity;
    private int currentCapacity;
    public ArrayList<StandardContainer> containers;

    public Warehouse(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.containers = new ArrayList<>();
    }

    public void addContainer(StandardContainer container) {
        if (this.currentCapacity < this.maxCapacity) {
            this.containers.add(container);
            this.currentCapacity++;
        }
        else{
            ConsoleColors.printRed("Warehouse is full!");
        }
    }

    public void removeContainer(StandardContainer container) {
        if (this.containers.contains(container)) {
            this.containers.remove(container);
            this.currentCapacity--;
        }
        else{
            ConsoleColors.printRed("Container is not in the warehouse!");
        }
    }

    public ArrayList<StandardContainer> getContainers() {
        return this.containers;
    }
}
