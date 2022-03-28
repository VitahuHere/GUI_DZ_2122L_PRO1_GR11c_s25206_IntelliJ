package containers.classes;

import utils.ConsoleColors;
import utils.Evaluators;
import utils.Status;

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

    public Status status;
    public int shipId;

    public int daysStored;

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
        this.status = Status.AVAILABLE;
        this.shipId = -1;
        this.daysStored = 0;
    }

    public StandardContainer(){
        this.id = containerIndex++;
        this.tare = Evaluators.getIntFromInput("Tare");
        this.size = Evaluators.getIntFromInput("Size");
        this.cargoWeight = Evaluators.getIntFromInput("Cargo weight");
        this.totalWeight = cargoWeight + tare;
        this.safetyMeasures = Evaluators.getArrayListFromInput("safety measures");
        this.certificates = Evaluators.getArrayListFromInput("certificates");
        this.status = Status.AVAILABLE;
        this.shipId = -1;
        this.daysStored = 0;
        ConsoleColors.printGreen("Successfully created container!");
        ConsoleColors.printYellow(this.toString());
    }

    @Override
    public String toString() {
        return "Container type: " +
                this.getClass().getSimpleName() +
                ", \nid: " + id +
                ", \ntare: " + tare +
                ", \nsize: " + size +
                ", \ncargo weight: " + cargoWeight +
                ", \ntotal weight: " + totalWeight +
                ", \nsafety measures: " + safetyMeasures +
                ", \ncertificates: " + certificates +
                ", \nstatus: " + status +
                ", \nship id: " + shipId + "\n";
    }
}
