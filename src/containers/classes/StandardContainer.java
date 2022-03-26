package containers.classes;

import utils.Evaluators;

import java.util.ArrayList;

public class StandardContainer {
    public static int containerIndex = 1;
    public final int id;
    public final int tare;
    public final int size;

    public int cargoWeight;
    public int totalWeight;

    public ArrayList<String> safetyMeasures;
    public ArrayList<String> certificates;

    public StandardContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates
    ) {
        this.id = containerIndex++;
        this.tare = tare;
        this.size = size;
        this.cargoWeight = cargoWeight;
        this.totalWeight = cargoWeight + tare;
        this.safetyMeasures = safetyMeasures;
        this.certificates = certificates;
    }

    public StandardContainer(){
        this.id = containerIndex++;
        this.tare = Evaluators.getIntFromInput("Tare");
        this.size = Evaluators.getIntFromInput("Size");
        this.cargoWeight = Evaluators.getIntFromInput("Cargo weight");
        this.totalWeight = cargoWeight + tare;
        this.safetyMeasures = Evaluators.getArrayListFromInput("safety measures");
        this.certificates = Evaluators.getArrayListFromInput("certificates");
    }

    @Override
    public String toString() {
        return "StandardContainer type: " +
                this.getClass().getSimpleName() +
                ", id: " + id +
                ", tare: " + tare +
                ", size: " + size +
                ", cargo weight: " + cargoWeight +
                ", total weight: " + totalWeight +
                ", safety measures: " + safetyMeasures +
                ", certificates: " + certificates;
    }
}
