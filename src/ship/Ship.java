// Written by Cong Minh Vu s25206

package ship;

import containers.abstracts.ToxicAbstract;
import containers.classes.*;
import containers.interfaces.ElectricInterface;
import utils.ConsoleColors;

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
                    "Setting maximum numbers of containers to their sum"
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
                ", name: '" + name + '\'' +
                ", home port: '" + homePort + '\'' +
                ", maximum payload weight: " + maxPayloadWeight +
                ", maximum containers count: " + maxContainersCount +
                ", maximum toxic/explosive containers count: " + maxToxicExplosiveContainersCount +
                ", maximum heavy containers count: " + maxHeavyContainersCount +
                ", maximum electric container count: " + maxElectricContainerCount +
                ", cargo: " + cargo +
                ", cargo weight: " + cargoWeight +
                ", number of toxic/explosive containers: " + toxicExplosiveCounter +
                ", number of heavy containers: " + heavyCounter +
                ", number of electric containers: " + electricCounter +
                ", departure port: '" + departurePort + '\'' +
                ", arrival port: '" + arrivalPort;
    }
}
