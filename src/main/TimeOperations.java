package main;

import containers.classes.ExplosivesContainer;
import containers.classes.StandardContainer;
import containers.classes.ToxicLiquidContainer;
import containers.classes.ToxicLooseMaterialContainer;
import exceptions.IrresponsibleSenderWithDangerousGood;
import port.Port;
import port.Train;
import utils.ConsoleColors;
import utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimerTask;

public class TimeOperations extends Thread {
    public static LocalDate currentDate = Constants.startDate;
    static boolean waitingNewTrain = false;
    static int trainTick = 0;
    int ticks = 0;
    int daysPassed = 0;

    public static void newTrainDelay() {
        waitingNewTrain = true;
        if (trainTick >= Constants.TRAIN_DELAY) {
            waitingNewTrain = false;
            Port.train = new Train();
            ConsoleColors.printGreen("New Train arrived.");
            trainTick = 0;
        } else {
            trainTick++;
        }
    }

    public static int getRemainingTime() {
        return Constants.TRAIN_DELAY - trainTick;
    }

    private void checkContainers() {
        ArrayList<StandardContainer> toBeRemoved = new ArrayList<>();
        for (StandardContainer container : Port.warehouse.getContainers()) {
            try {
                if (container instanceof ExplosivesContainer && container.daysLeft() <= 0 ||
                        container instanceof ToxicLiquidContainer && container.daysLeft() <= 0 ||
                        container instanceof ToxicLooseMaterialContainer && container.daysLeft() <= 0
                ) {
                    throw new IrresponsibleSenderWithDangerousGood(container);
                }
            } catch (IrresponsibleSenderWithDangerousGood e) {
                ConsoleColors.printRed(e.getMessage());
                container.sender.strikes++;
                toBeRemoved.add(container);
            }
        }
        for (StandardContainer container : toBeRemoved) {
            Port.warehouse.removeContainer(container);
            App.removedIds.add(container.id);
        }
    }

    private boolean newDay() {
        if (ticks == 5) {
            ticks = 0;
            daysPassed++;
            currentDate = currentDate.plusDays(1);
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while(Main.mainThread.isInterrupted()){
            ticks++;
            if (newDay()) {
                checkContainers();
            }
            if (waitingNewTrain) {
                newTrainDelay();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
