package port;

import containers.classes.StandardContainer;
import main.TimeOperations;
import utils.Constants;

import java.util.ArrayList;

public class Warehouse {
    public final int maxCapacity;
    private int currentCapacity;
    private final ArrayList<StandardContainer> containers;

    public Warehouse(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.containers = new ArrayList<>();
    }

    private int checkContainerType(StandardContainer container) {
        return switch (container.getClass().getSimpleName()) {
            case "ExplosivesContainer" -> Constants.EXPLOSIVES_MAX_DAYS;
            case "ToxicLiquidContainer" -> Constants.TOXIC_LIQUID_MAX_DAYS;
            case "ToxicLooseMaterialContainer" -> Constants.TOXIC_LOOSE_MAX_DAYS;
            default -> 0;
        };
    }

    public void addContainer(StandardContainer container) {
        if (this.currentCapacity < this.maxCapacity) {
            this.containers.add(container);
            this.currentCapacity++;
            container.arrivalDate = TimeOperations.currentDate;
            container.dueDate = container.arrivalDate.plusDays(checkContainerType(container)) == container.arrivalDate ? null : container.arrivalDate.plusDays(checkContainerType(container));
        }
        else{
            System.out.println("Warehouse is full!");
        }
    }

    public void removeContainer(StandardContainer container) {
        if (this.containers.contains(container)) {
            this.containers.remove(container);
            this.currentCapacity--;
        }
        else{
            System.out.println("Container is not in the warehouse!");
        }
    }

    public ArrayList<StandardContainer> getContainers() {
        return this.containers;
    }

    @Override
    public String toString() {
        return "Warehouse " +
                " current capacity:" + currentCapacity +
                ", containers: " + containers;
    }
}
