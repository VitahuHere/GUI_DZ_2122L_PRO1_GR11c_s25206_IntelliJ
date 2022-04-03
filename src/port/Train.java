package port;

import app.App;
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

    public Train() {
        this.id = trainIndex++;
        this.MAX_CAPACITY = Constants.MAX_TRAIN_CAPACITY;
        this.currentCapacity = 0;
        this.containers = new ArrayList<>();
        App.trains.add(this);
    }

    public void addContainer(StandardContainer container) {
        if(this.currentCapacity < this.MAX_CAPACITY) {
            this.containers.add(container);
            this.currentCapacity++;
        }
        else{
            ConsoleColors.printRed("Train is full");
        }
        if(this.currentCapacity == this.MAX_CAPACITY) {
            Port.train = new Train();
        }
    }

    @Override
    public String toString() {
        return "Train{" +
                "Train id=" + id +
                ", maximum capacity: " + MAX_CAPACITY +
                ", current capacity=" + currentCapacity +
                ", containers=" + containers +
                '}';
    }
}
