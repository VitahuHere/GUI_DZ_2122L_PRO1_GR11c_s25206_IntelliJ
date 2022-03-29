package port;

import containers.classes.StandardContainer;
import utils.ConsoleColors;
import utils.Constants;

import java.util.ArrayList;

public class Train {
    public final int MAX_CAPACITY;
    public int currentCapacity;

    private static int trainIndex = 0;
    private final int id;
    private final ArrayList<StandardContainer> containers;

    public Train(int maxCapacity) {
        this.id = trainIndex++;
        this.MAX_CAPACITY = Constants.MAX_TRAIN_CAPACITY;
        this.currentCapacity = 0;
        this.containers = new ArrayList<>();
    }

    public void addContainer(StandardContainer container) {
        if(this.currentCapacity < this.MAX_CAPACITY) {
            this.containers.add(container);
            this.currentCapacity++;
        }
        else{
            ConsoleColors.printRed("Train is full");
        }
    }

    public void removeContainer(StandardContainer container) {
        if(this.containers.contains(container)) {
            this.containers.remove(container);
            this.currentCapacity--;
        }
        else{
            ConsoleColors.printRed("Container not found");
        }
    }
}
