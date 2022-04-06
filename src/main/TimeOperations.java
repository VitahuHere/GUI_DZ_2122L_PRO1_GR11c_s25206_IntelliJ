package main;

import containers.classes.ExplosivesContainer;
import containers.classes.StandardContainer;
import containers.classes.ToxicLiquidContainer;
import containers.classes.ToxicLooseMaterialContainer;
import port.Port;
import utils.ConsoleColors;
import utils.Constants;

import java.util.TimerTask;

public class TimeOperations extends TimerTask {
    int ticks = 0;

    private void removeFromWarehouse(StandardContainer container) {
        ConsoleColors.printRed("Container " + container.id + " exceeded maximum allowed storing time. Removing From warehouse.");
        Port.warehouse.containers.remove(container);
    }

    private void checkContainers(){
        for (StandardContainer container : Port.warehouse.containers) {
            if (container instanceof ExplosivesContainer && container.daysStored == Constants.EXPLOSIVES_MAX_DAYS) {
                removeFromWarehouse(container);
            } else if (container instanceof ToxicLiquidContainer && container.daysStored == Constants.TOXIC_LIQUID_MAX_DAYS) {
                removeFromWarehouse(container);
            } else if (container instanceof ToxicLooseMaterialContainer && container.daysStored == Constants.TOXIC_LOOSE_MAX_DAYS) {
                removeFromWarehouse(container);
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

    }

    @Override
    public void run() {
        ticks++;
        if(newDay()){
            checkContainers();
        }
    }
}
