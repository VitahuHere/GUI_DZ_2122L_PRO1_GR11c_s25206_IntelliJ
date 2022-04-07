package main;

import containers.classes.ExplosivesContainer;
import containers.classes.StandardContainer;
import containers.classes.ToxicLiquidContainer;
import containers.classes.ToxicLooseMaterialContainer;
import port.Port;
import port.Train;
import utils.ConsoleColors;
import utils.Constants;

import java.util.TimerTask;

public class TimeOperations extends TimerTask {
    int ticks = 0;
    static boolean waitingNewTrain = false;
    static int trainTick = 0;

    private void removeFromWarehouse(StandardContainer container) {
        ConsoleColors.printRed("Container " + container.id + " exceeded maximum allowed storing time. Removing From warehouse.");
        Port.warehouse.containers.remove(container);
    }

    private void checkContainers(){
        for (StandardContainer container : Port.warehouse.containers) {
            if (container instanceof ExplosivesContainer && container.daysStored == Constants.EXPLOSIVES_MAX_DAYS) {
                removeFromWarehouse(container);
                break;
            } else if (container instanceof ToxicLiquidContainer && container.daysStored == Constants.TOXIC_LIQUID_MAX_DAYS) {
                removeFromWarehouse(container);
                break;
            } else if (container instanceof ToxicLooseMaterialContainer && container.daysStored == Constants.TOXIC_LOOSE_MAX_DAYS) {
                removeFromWarehouse(container);
                break;
            }
            else{
                container.daysStored++;
            }
        }
    }

    private boolean newDay(){
        if(ticks == 5){
            ticks = 0;
            return true;
        }
        return false;
    }

    public static void newTrainDelay(){
        waitingNewTrain = true;
        if(trainTick >= Constants.TRAIN_DELAY){
            waitingNewTrain = false;
            Port.train = new Train();
            ConsoleColors.printGreen("New Train arrived.");
            trainTick = 0;
        }
        else{
            trainTick++;
        }
    }

    public static int getRemainingTime(){
        return Constants.TRAIN_DELAY - trainTick;
    }

    @Override
    public void run() {
        ticks++;
        if(newDay()){
            checkContainers();
        }
        if(waitingNewTrain){
            newTrainDelay();
        }
    }
}
