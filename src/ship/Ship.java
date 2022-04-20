package ship;

import main.App;
import containers.classes.StandardContainer;
import port.Port;
import utils.ConsoleColors;
import utils.Constants;
import utils.Evaluators;
import utils.Returns;

import java.util.ArrayList;

public class Ship implements Comparable<Ship> {
    public static int shipIndex = 1;

    public final int id;
    public final String name;
    public final String homePort;
    public final int maxCargoWeight;
    public final int maxContainersCount;
    public final int maxToxicExplosiveContainersCount;
    public final int maxHeavyContainersCount;
    public final int maxElectricContainersCount;
    public final ArrayList<StandardContainer> containers;

    // helper fields
    int cargoWeight;
    int toxicExplosiveCounter;
    int heavyCounter;
    int electricCounter;
    int slotsAvailable;


    public String departurePort;
    public String arrivalPort;

    public Ship(
            String name,
            String homePort,
            int maxCargoWeight,
            int maxContainersCount,
            int maxToxicExplosiveContainersCount,
            int maxHeavyContainersCount,
            int maxElectricContainersCount
    ) {
        this.containers = new ArrayList<>();
        this.cargoWeight = 0;
        this.toxicExplosiveCounter = 0;
        this.heavyCounter = 0;
        this.electricCounter = 0;

        this.id = shipIndex++;
        this.name = name;
        this.homePort = homePort;
        this.maxCargoWeight = checkPositiveValue(maxCargoWeight);
        this.maxContainersCount = checkPositiveValue(maxContainersCount);
        this.maxToxicExplosiveContainersCount = maxToxicExplosiveContainersCount;
        this.maxHeavyContainersCount = maxHeavyContainersCount;
        this.maxElectricContainersCount = maxElectricContainersCount;
        this.slotsAvailable = this.maxContainersCount - this.maxToxicExplosiveContainersCount - this.maxHeavyContainersCount - this.maxElectricContainersCount;
        Port.ships.add(this);
    }

    public Ship() {
        this.containers = new ArrayList<>();
        this.cargoWeight = 0;
        this.toxicExplosiveCounter = 0;
        this.heavyCounter = 0;
        this.electricCounter = 0;


        this.id = shipIndex++;
        this.name = Evaluators.getStringFromInput("Name");
        this.homePort = Evaluators.getStringFromInput("Home port");
        this.maxCargoWeight = Evaluators.getIntFromInput("Maximum cargo weight");
        this.maxContainersCount = Evaluators.getIntFromInput("Maximum number of containers");
        this.slotsAvailable = this.maxContainersCount;

        this.maxToxicExplosiveContainersCount = checkSlotsLeft(Evaluators.getIntFromInput("Maximum number of toxic and explosive containers"));
        this.maxHeavyContainersCount = checkSlotsLeft(Evaluators.getIntFromInput("Maximum number of heavy containers"));
        this.maxElectricContainersCount = checkSlotsLeft(Evaluators.getIntFromInput("Maximum number of containers requiring electricity"));
        Port.ships.add(this);

        ConsoleColors.printGreen("Successfully created new Ship!");
    }

    public void addContainerOfType(StandardContainer container) {
        // spent too much time trying to optimise this and nothing works>:((
        String containerType = container.getClass().getSimpleName();
        switch (containerType) {
            case "ExplosivesContainer":
            case "ToxicLiquidContainer":
            case "ToxicLooseMaterialContainer":
                if (this.toxicExplosiveCounter < this.maxToxicExplosiveContainersCount) {
                    addContainer(container);
                    this.toxicExplosiveCounter++;
                    this.heavyCounter++;
                } else {
                    ConsoleColors.printRed(Constants.LIMIT_CONTAINER_TYPE);
                }
                break;
            case "ChillerContainer":
                if (this.electricCounter < this.maxElectricContainersCount) {
                    addContainer(container);
                    this.electricCounter++;
                    this.heavyCounter++;
                } else {
                    ConsoleColors.printRed(Constants.LIMIT_CONTAINER_TYPE);
                }
                break;
            case "HeavyContainer":
                if (this.heavyCounter < this.maxHeavyContainersCount) {
                    addContainer(container);
                    this.heavyCounter++;
                } else {
                    ConsoleColors.printRed(Constants.LIMIT_CONTAINER_TYPE);
                }
                break;
            default:
                addContainer(container);
        }
    }

    public void removeContainerOfType(StandardContainer container) {
        if (this.containers.contains(container)) {
            String containerType = container.getClass().getSimpleName();
            switch(containerType){
                case "ExplosivesContainer":
                case "ToxicLiquidContainer":
                case "ToxicLooseMaterialContainer":
                    this.toxicExplosiveCounter--;
                    this.heavyCounter--;
                    break;
                case "HeavyContainer":
                    this.heavyCounter--;
                case "ChillerContainer":
                    this.electricCounter--;
                    this.heavyCounter--;
            }
            removeContainer(container);
        } else {
            ConsoleColors.printRed("Container is not on this ship. Cannot offload.");
        }
    }

    private void addContainer(StandardContainer container) {
        this.containers.add(container);
        this.cargoWeight += container.totalWeight;
        App.containers.remove(container);
    }

    private void removeContainer(StandardContainer container) {
        this.containers.remove(container);
        this.cargoWeight -= container.totalWeight;
    }

    private int checkPositiveValue(int value) {
        if (value < 0) {
            ConsoleColors.printRed("Value is negative. Setting to 0");
            return 0;
        }
        return value;
    }

    private int checkSlotsLeft(int value) {
        if (this.slotsAvailable <= 0) {
            ConsoleColors.printRed("No slots available. Setting value to 0");
            return 0;
        } else if (this.slotsAvailable - value < 0) {
            ConsoleColors.printRed("Value entered exceed slots available left. Please re-enter: ");
            value = Evaluators.getIntFromInput(this.slotsAvailable);
        }
        this.slotsAvailable -= value;
        ConsoleColors.printBlue(this.slotsAvailable + " slots left");
        return value;
    }

    public int getSlotsAvailable() {
        return maxContainersCount - containers.size();
    }

    public int getWeightAvailable() {
        return maxCargoWeight - cargoWeight;
    }

    @Override
    public String toString() {
        return "Ship information: " +
                "id: " + id +
                ", \nname: '" + name + '\'' +
                ", \nhome port: '" + homePort + '\'' +
                ", \nmaximum payload weight: " + maxCargoWeight +
                ", \nmaximum containers count: " + maxContainersCount +
                ", \nmaximum toxic/explosive containers count: " + maxToxicExplosiveContainersCount +
                ", \nmaximum heavy containers count: " + maxHeavyContainersCount +
                ", \nmaximum electric container count: " + maxElectricContainersCount +
                ", \ncargo weight: " + cargoWeight +
                ", \ndeparture port: " + (departurePort == null ? "n/a" : departurePort) +
                ", \narrival port: " + (arrivalPort == null ? "n/a" : departurePort) +
                ", \ncontainers: " + (Returns.listContainers(containers).size() == 0 ? "n/a" : Returns.listContainers(containers));
    }

    public String toSaveString() {
        return "\n\ttype: Ship" +
                ", \n\tid: " + id +
                ", \n\tname: '" + name + '\'' +
                ", \n\thomePort: '" + homePort + '\'' +
                ", \n\tmaxCargoWeight: " + maxCargoWeight +
                ", \n\tmaxContainersCount: " + maxContainersCount +
                ", \n\tmaxToxicExplosiveContainersCount: " + maxToxicExplosiveContainersCount +
                ", \n\tmaxHeavyContainersCount: " + maxHeavyContainersCount +
                ", \n\tmaxElectricContainersCount: " + maxElectricContainersCount +
                ", \n\tcargoWeight: " + cargoWeight +
                ", \n\ttoxicExplosiveCounter: " + toxicExplosiveCounter +
                ", \n\theavyCounter: " + heavyCounter +
                ", \n\telectricCounter: " + electricCounter +
                ", \n\tslotsAvailable: " + slotsAvailable +
                ", \n\tdeparturePort: '" + departurePort + '\'' +
                ", \n\tarrivalPort: '" + arrivalPort + '\'' +
                ", \n\tcontainers: " + Returns.listContainersToSave(containers);
    }

    @Override
    public int compareTo(Ship o) {
        return this.name.compareTo(o.name);
    }
}
