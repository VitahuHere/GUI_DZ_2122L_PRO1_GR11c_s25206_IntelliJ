package warehouse;

import containers.classes.StandardContainer;
import utils.ConsoleColors;
import utils.Status;

import java.util.ArrayList;

public class Warehouse {
    private final int maxCapacity;

    private int currentCapacity;
    private final ArrayList<StandardContainer> containers;

    public Warehouse(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.containers = new ArrayList<>();
    }

    public void addContainer(StandardContainer container) {
        if (this.currentCapacity < this.maxCapacity) {
            if(container.status == Status.AT_WAREHOUSE){
                ConsoleColors.printRed("Container is already at warehouse");
            }
            else {
                this.containers.add(container);
                this.currentCapacity++;
            }
        }
        else{
            ConsoleColors.printRed("Warehouse is full. Cannot add container");
        }
    }

    public void removeContainer(StandardContainer container) {
        if (this.containers.contains(container)) {
            this.containers.remove(container);
            this.currentCapacity--;
        }
        else{
            ConsoleColors.printRed("Container is not at warehouse");
        }
    }
}
