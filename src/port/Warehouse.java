package port;

import containers.classes.StandardContainer;
import main.TimeOperations;
import utils.Constants;
import utils.Returns;

import java.util.ArrayList;

import static utils.Returns.listContainersToSave;

public class Warehouse {
    public final int maxCapacity;
    private final ArrayList<StandardContainer> containers;
    private int currentCapacity;

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
        } else {
            System.out.println("Warehouse is full!");
        }
    }

    public void removeContainer(StandardContainer container) {
        if (this.containers.contains(container)) {
            this.containers.remove(container);
            this.currentCapacity--;
        } else {
            System.out.println("Container is not in the warehouse!");
        }
    }

    public ArrayList<StandardContainer> getContainers() {
        return this.containers;
    }

    @Override
    public String toString() {
        return "Warehouse" +
                ", current capacity:" + currentCapacity +
                ", containers: " + Returns.listContainers(this.containers);
    }

    public String toSaveString() {
        return "\n\ttype: Warehouse" +
                ", \n\tcurrent capacity: " + currentCapacity +
                ", \n\tcontainers: " + listContainersToSave(containers);
    }
}
