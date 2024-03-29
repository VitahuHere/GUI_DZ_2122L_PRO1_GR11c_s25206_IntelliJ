package containers.classes;

import main.App;
import main.TimeOperations;
import port.Port;
import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

import java.time.LocalDate;
import java.util.ArrayList;

public class StandardContainer implements Comparable<StandardContainer> {
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
        this.safetyMeasures = Evaluators.getArrayListFromInput("Safety measures");
        this.certificates = Evaluators.getArrayListFromInput("Certificates");
        ConsoleColors.printGreen("Successfully created container!");
        App.containers.add(this);
    }

    public int daysLeft() {
        if (dueDate == null) {
            return 1;
        }
        return TimeOperations.currentDate.until(dueDate).getDays();
    }

    @Override
    public String toString() {
        return "Container id: " + id +
                ", type: " + this.getClass().getSimpleName() +
                ", tare: " + tare +
                ", size: " + size +
                ", cargo weight: " + cargoWeight +
                ", total weight: " + totalWeight +
                ", safety measures: " + (safetyMeasures.size() == 0 ? "None" : safetyMeasures) +
                ", certificates: " + (certificates.size() == 0 ? "None" : certificates) +
                ", arrival date: " + (arrivalDate == null ? "not set" : arrivalDate) +
                ", due date: " + (dueDate == null ? "not set" : dueDate) +
                ", senders name: " + (sender == null ? "not set" : sender.name);
    }

    public String toSaveString() {
        return "\n\ttype: " + this.getClass().getSimpleName() +
                ", \n\tid: " + id +
                ", \n\ttare: " + tare +
                ", \n\tsize: " + size +
                ", \n\tcargoWeight: " + cargoWeight +
                ", \n\ttotalWeight: " + totalWeight +
                ", \n\tsafetyMeasures: " + safetyMeasures +
                ", \n\tcertificates: " + certificates +
                ", \n\tsender: " + (sender != null ? sender.PESEL : "null") +
                ", \n\tarrivalDate: " + arrivalDate +
                ", \n\tdueDate: " + dueDate;
    }

    @Override
    public int compareTo(StandardContainer o) {
        if (Port.warehouse.getContainers().contains(this) && Port.warehouse.getContainers().contains(o)) {
            if (this.arrivalDate.compareTo(o.arrivalDate) == 0) {
                if (this.sender != null && o.sender != null){
                    return (this.sender.name + this.sender.surname).compareTo(o.sender.name + o.sender.surname);
                }
                else if (this.sender == null && o.sender != null){
                    return "null".compareTo(o.sender.name + o.sender.surname);
                }
                else if (this.sender != null){
                    return (this.sender.name + this.sender.surname).compareTo("null");
                }
            }
            return this.arrivalDate.compareTo(o.arrivalDate);
        }
        return this.totalWeight - o.totalWeight;
    }
}
