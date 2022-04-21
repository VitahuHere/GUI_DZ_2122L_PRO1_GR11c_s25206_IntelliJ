package containers.classes;

import containers.interfaces.LiquidInterface;
import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class LiquidsContainer extends StandardContainer implements LiquidInterface {
    public final int maxCapacity;

    public LiquidsContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int maxCapacity,
            Sender sender
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, sender);
        this.maxCapacity = maxCapacity;
    }

    public LiquidsContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo weight"),
                Evaluators.getArrayListFromInput("Safety measures"),
                Evaluators.getArrayListFromInput("Certificates")
        );
        this.maxCapacity = Evaluators.getIntFromInput("Maximum capacity");
        ConsoleColors.printGreen("Successfully created container!");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", max capacity=" + maxCapacity +
                ", valve diameter=" + valveDiameter;
    }

    @Override
    public String toSaveString(){
        return super.toSaveString() +
                ", \n\tmaxCapacity: " + maxCapacity +
                ", \n\tvalveDiameter: " + valveDiameter;
    }
}
