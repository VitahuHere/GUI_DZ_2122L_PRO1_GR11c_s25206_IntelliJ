package containers.classes;

import main.App;
import main.TimeOperations;
import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

import java.time.LocalDate;
import java.util.ArrayList;

public class StandardContainer {
    public static int containerIndex = 1;
    public final int id;
    public final int tare;
    public final int size;

    public int cargoWeight;
    public int totalWeight; // cargo weight + tare

    public ArrayList<String> safetyMeasures;
    public ArrayList<String> certificates;

    public Sender sender;

    public LocalDate arrivalDate;
    public LocalDate dueDate;

    protected StandardContainer(
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
        App.containers.add(this);
    }

    public StandardContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            Sender sender
    ) {
        this(tare, size, cargoWeight, safetyMeasures, certificates);
        this.sender = sender;
    }

    public StandardContainer() {
        this.id = containerIndex++;
        this.tare = Evaluators.getIntFromInput("Tare");
        this.size = Evaluators.getIntFromInput("Size");
        this.cargoWeight = Evaluators.getIntFromInput("Cargo weight");
        this.totalWeight = cargoWeight + tare;
        this.safetyMeasures = Evaluators.getArrayListFromInput("safety measures");
        this.certificates = Evaluators.getArrayListFromInput("certificates");
        ConsoleColors.printGreen("Successfully created container!");
        App.containers.add(this);
    }

    public int daysLeft() {
        if(dueDate == null) {
            return 1;
        }
        return TimeOperations.currentDate.until(dueDate).getDays();
    }

    @Override
    public String toString() {
        return "\nContainer id: " + id +
                ", \ntype: " + this.getClass().getSimpleName() +
                ", \ntare: " + tare +
                ", \nsize: " + size +
                ", \ncargo weight: " + cargoWeight +
                ", \ntotal weight: " + totalWeight +
                ", \nsafety measures: " + (safetyMeasures.size() == 0 ? "None" : safetyMeasures) +
                ", \ncertificates: " + (certificates.size() == 0 ? "None" : certificates) +
                ", \narrival date: " + (arrivalDate == null ? "not set" : arrivalDate) +
                ", \ndue date: " + (dueDate == null ? "not set" : dueDate) +
                ", \nsenders name: " + (sender == null ? "not set" : sender.name);
    }
}
