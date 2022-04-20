package port;

import main.App;
import containers.classes.StandardContainer;
import main.TimeOperations;
import utils.ConsoleColors;
import utils.Constants;
import utils.Returns;

import java.util.ArrayList;

public class Train {
    public final int MAX_CAPACITY;
    public int currentCapacity;

    private static int trainIndex = 0;
    private final int id;
    private final ArrayList<StandardContainer> containers;

    private boolean isFull;

    public Train() {
        this.id = trainIndex++;
        this.MAX_CAPACITY = Constants.MAX_TRAIN_CAPACITY;
        this.currentCapacity = 0;
        this.containers = new ArrayList<>();
        App.trains.add(this);
        this.isFull = false;
    }

    public void addContainer(StandardContainer container) {
        if(this.currentCapacity < this.MAX_CAPACITY) {
            this.containers.add(container);
            this.currentCapacity++;
        }
        else{
            if(!isFull){
                TimeOperations.newTrainDelay();
            }
            ConsoleColors.printRed("Train is full. Waiting for a new train. Please wait " + TimeOperations.getRemainingTime() + " seconds.");
            isFull = true;
        }
    }

    public ArrayList<StandardContainer> getContainers() {
        return containers;
    }

    @Override
    public String toString() {
        return "Train: " +
                "Train id: " + id +
                ", maximum capacity: " + MAX_CAPACITY +
                ", current capacity: " + currentCapacity +
                ", containers: " + Returns.listContainers(containers);
    }

    public String toSaveString() {
        return "\n\ttype: Train" +
                ", \n\tMAX_CAPACITY: " + MAX_CAPACITY +
                ", \n\tcurrentCapacity: " + currentCapacity +
                ", \n\tid: " + id +
                ", \n\tcontainers: " + Returns.listContainersToSave(containers) +
                ", \n\tisFull: " + isFull;
    }
}
