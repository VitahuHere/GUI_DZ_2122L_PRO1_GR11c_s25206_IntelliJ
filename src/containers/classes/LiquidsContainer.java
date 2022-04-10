package containers.classes;

import java.util.ArrayList;

import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

public class LiquidsContainer extends StandardContainer {
    public final int maxCapacity;
    public final int valveDiameter;

//    public LiquidsContainer(
//            int tare,
//            int size,
//            int cargoWeight,
//            ArrayList<String> safetyMeasures,
//            ArrayList<String> certificates,
//            int maxCapacity,
//            int valveDiameter
//    ) {
//        super(tare, size, cargoWeight, safetyMeasures, certificates);
//        this.maxCapacity = maxCapacity;
//        this.valveDiameter = valveDiameter;
//    }

    public LiquidsContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int maxCapacity,
            int valveDiameter,
            Sender sender
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, sender);
        this.maxCapacity = maxCapacity;
        this.valveDiameter = valveDiameter;
    }

    public LiquidsContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo weigh"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates")
        );
        this.maxCapacity = Evaluators.getIntFromInput("Maximum capacity");
        this.valveDiameter = Evaluators.getIntFromInput("Valve diameter");
        ConsoleColors.printGreen("Successfully created container!");
        ConsoleColors.printYellow(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", \nmax capacity=" + maxCapacity +
                ", \nvalve diameter=" + valveDiameter;
    }
}
