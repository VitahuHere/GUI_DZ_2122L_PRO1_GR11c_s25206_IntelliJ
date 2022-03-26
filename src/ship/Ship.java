// Written by Cong Minh Vu s25206

package ship;

import containers.abstracts.ToxicAbstract;
import containers.classes.*;
import containers.interfaces.ElectricInterface;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class Ship {
    public static int shipIndex = 1;

    public final int id;
    public final String name;
    public final String homePort;
    public final int maxPayloadWeight;
    public final int maxContainersCount;
    public final int maxToxicExplosiveContainersCount;
    public final int maxHeavyContainersCount;
    public final int maxElectricContainerCount;

    private final ArrayList<StandardContainer> cargo;
    private int cargoWeight;
    private int toxicExplosiveCounter;
    private int heavyCounter;
    private int electricCounter;

    public String departurePort;
    public String arrivalPort;

    public Ship(
            String name,
            String homePort,
            int maxContainersCount,
            int maxPayloadWeight,
            int maxToxicExplosiveContainersCount,
            int maxHeavyContainersCount,
            int maxElectricContainerCount
    ) {
        this.id = shipIndex++;
        this.name = name;
        this.homePort = homePort;
        this.maxPayloadWeight = evaluateCount(maxPayloadWeight);
        this.maxToxicExplosiveContainersCount = evaluateCount(maxToxicExplosiveContainersCount);
        this.maxHeavyContainersCount = evaluateCount(maxHeavyContainersCount);
        this.maxElectricContainerCount = evaluateCount(maxElectricContainerCount);
        this.maxContainersCount = checkContainerCount(maxContainersCount);

        this.cargo = new ArrayList<>();
        this.cargoWeight = 0;
        this.toxicExplosiveCounter = 0;
        this.heavyCounter = 0;
        this.electricCounter = 0;
    }

    public Ship(){
        ConsoleColors.printYellow("Welcome to Ship creation page!");
        ConsoleColors.printYellow("Please enter following information:");
        this.id = shipIndex++;
        this.name = Evaluators.getStringInput("Name");
        this.homePort = Evaluators.getStringInput("Home port");
        this.maxPayloadWeight = Evaluators.getIntFromInput("Maximum payload weight");
        this.maxToxicExplosiveContainersCount = Evaluators.getIntFromInput("Maximum number of toxic and explosive containers");
        this.maxHeavyContainersCount = Evaluators.getIntFromInput("Maximum number of heavy containers");
        this.maxElectricContainerCount = Evaluators.getIntFromInput("Maximum number of containers requiring electricity");
        this.maxContainersCount = checkContainerCount(Evaluators.getIntFromInput("Maximum number of containers"));

        this.cargo = new ArrayList<>();
        this.cargoWeight = 0;
        this.toxicExplosiveCounter = 0;
        this.heavyCounter = 0;
        this.electricCounter = 0;
    }

    private int evaluateCount(int value) {
        return Math.max(value, 0);
    }

    // Checks if total specified containers count exceed max overall container count or is less than 0
    private int checkContainerCount(int maxContainersCount) {
        int containersSum = this.maxToxicExplosiveContainersCount + this.maxHeavyContainersCount + this.maxElectricContainerCount;
        if (containersSum > maxContainersCount) {
            ConsoleColors.printRed("ship id: " +
                    this.id +
                    " named: '" +
                    this.name +
                    "' sum of specified containers exceed maximum container capacity. " +
                    "Setting maximum numbers of containers to sum of specified containers."
            );
            return containersSum;
        } else if (maxContainersCount < 0) {
            ConsoleColors.printRed(
                    "ship id: " +
                            this.id +
                            " named: '" +
                            this.name +
                            "' maximum number of containers is below 0. Setting to 0."
            );
            return 0;
        }
        return maxContainersCount;
    }

    public void loadContainer(StandardContainer container) {
        boolean addToCargo = false;

        if (this.maxContainersCount <= this.cargo.size() || this.cargoWeight > this.maxPayloadWeight) {
            ConsoleColors.printRed("Maximum container count or mass reached. Cannot add another one.");
        } else {
            if ((container instanceof ExplosivesContainer || container instanceof ToxicAbstract) && this.toxicExplosiveCounter < this.maxToxicExplosiveContainersCount) {
                addToCargo = true;
                this.toxicExplosiveCounter++;
            } else if (container instanceof HeavyContainer && this.heavyCounter < this.maxHeavyContainersCount) {
                addToCargo = true;
                this.heavyCounter++;
            } else if (container instanceof ElectricInterface && this.electricCounter < this.maxElectricContainerCount) {
                addToCargo = true;
                this.electricCounter++;
            }

            if (addToCargo) {
                this.cargo.add(container);
                this.cargoWeight += container.totalWeight;
            }
            else{
                ConsoleColors.printRed("Reached maximum number of containers of this type. Cannot load more");
            }
        }
    }

    @Override
    public String toString() {
        return "Ship information: " +
                "id: " + id +
                ", \nname: '" + name + '\'' +
                ", \nhome port: '" + homePort + '\'' +
                ", \nmaximum payload weight: " + maxPayloadWeight +
                ", \nmaximum containers count: " + maxContainersCount +
                ", \nmaximum toxic/explosive containers count: " + maxToxicExplosiveContainersCount +
                ", \nmaximum heavy containers count: " + maxHeavyContainersCount +
                ", \nmaximum electric container count: " + maxElectricContainerCount +
                ", \ncargo weight: " + cargoWeight +
                ", \nnumber of toxic/explosive containers: " + toxicExplosiveCounter +
                ", \nnumber of heavy containers: " + heavyCounter +
                ", \nnumber of electric containers: " + electricCounter +
                ", \ndeparture port: '" + departurePort + '\'' +
                ", \narrival port: '" + arrivalPort;
    }
}
