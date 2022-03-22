// Written by Cong Minh Vu s25206

package ship;

import containers.abstracts.ToxicAbstract;
import containers.classes.*;
import containers.interfaces.ElectricInterface;
import utils.ConsoleColors;
import utils.Statics;

import java.util.ArrayList;

public class Ship {

    public final int id;
    public final String name;
    public final String homePort;
    public final int maxPayloadWeight;
    public final int maxContainersCount;
    public final int maxToxicExplosiveContainersCount;
    public final int maxHeavyContainersCount;
    public final int maxElectricContainerCount;

    private final ArrayList<Container> cargo;
    private int cargoWeight;
    private int toxicExplosiveCounter;
    private int heavyCounter;
    private int electricCounter;

    private String departurePort;
    private String arrivalPort;

    /**
     * id {@value Statics#shipIndex} identifier for ship. Unique at creation. Increments with each new one.
     *
     * @param name                             name of the ship.
     * @param homePort                         port of origin. Also called Mother Port.
     * @param maxContainersCount               maximum number of containers possible to safely fit onto the ship.
     * @param maxPayloadWeight                 maximum total weight of payload possible to safely swim to destination.
     * @param maxToxicExplosiveContainersCount determines maximum number of toxic and explosive containers that can be places onto the ship
     * @param maxHeavyContainersCount          determines maximum number of heavy containers that can be places onto the ship
     * @param maxElectricContainerCount        determines maximum number of containers that require to be connected to electric power
     */
    public Ship(
            String name,
            String homePort,
            int maxContainersCount,
            int maxPayloadWeight,
            int maxToxicExplosiveContainersCount,
            int maxHeavyContainersCount,
            int maxElectricContainerCount
    ) {
        this.id = Statics.shipIndex++;
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

    private int evaluateCount(int value){
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
        }
        else if (maxContainersCount < 0) {
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

    // Setters
    public void setArrivalPort(String arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public void setDeparturePort(String departurePort) {
        this.departurePort = departurePort;
    }

    // Getters
    public String getDeparturePort() {
        return departurePort;
    }

    public String getArrivalPort() {
        return arrivalPort;
    }

    public void loadContainer(Container container) {
        if (this.maxContainersCount <= this.cargo.size()) {
            ConsoleColors.printRed("Maximum container count reached. Cannot add another one.");
        }
        else {
            if (container instanceof ExplosivesContainer || container instanceof ToxicAbstract) {
                if(this.toxicExplosiveCounter < this.maxToxicExplosiveContainersCount){
                    this.cargo.add(container);
                    this.toxicExplosiveCounter++;
                    this.cargoWeight += container.totalWeight;
                }
                else{
                    ConsoleColors.printRed("Maximum number of Toxic or Explosive containers reached. Cannot load more.");
                }
            }
            else if(container instanceof HeavyContainer){
                if(this.heavyCounter < this.maxHeavyContainersCount){
                    this.cargo.add(container);
                    this.heavyCounter++;
                    this.cargoWeight += container.totalWeight;
                }
                else{
                    ConsoleColors.printRed("Maximum number of Heavy containers reached. Cannot load more");
                }
            }
            else if(container instanceof ElectricInterface){
                if(this.electricCounter < this.maxElectricContainerCount){
                    this.cargo.add(container);
                    this.electricCounter++;
                    this.cargoWeight += container.totalWeight;
                }
                else{
                    ConsoleColors.printRed("Maximum number of Electric containers reached. Cannot load more");
                }
            }
            else{
                this.cargo.add(container);
                this.cargoWeight += container.totalWeight;
            }
        }
    }

    @Override
    public String toString() {
        return "Ship information {" +
                "id: " + id +
                ", name: '" + name + '\'' +
                ", homePort: '" + homePort + '\'' +
                ", maxPayloadWeight: " + maxPayloadWeight +
                ", maxContainersCount: " + maxContainersCount +
                ", maxToxicExplosiveContainersCount: " + maxToxicExplosiveContainersCount +
                ", maxHeavyContainersCount: " + maxHeavyContainersCount +
                ", maxElectricContainerCount: " + maxElectricContainerCount +
                ", departurePort: '" + departurePort + '\'' +
                ", arrivalPort: '" + arrivalPort + '\'' +
                '}';
    }
}
