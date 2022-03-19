// Written by Cong Minh Vu s25206

package ship;

import main.Statics;

import java.util.Scanner;

public class Ship {

    // Ships immutable data
    public final int id;
    public final String name;
    public final String homePort;
    public final int maxPayloadWeight;
    public final int maxContainersCount;
    public final int maxToxicExplosiveContainersCount;
    public final int maxHeavyContainersCount;
    public final int maxElectricContainerCount;

    // Ship can change departure and arrival ports
    private String departurePort;
    private String arrivalPort;

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
        this.maxPayloadWeight = maxPayloadWeight;
        this.maxToxicExplosiveContainersCount = maxToxicExplosiveContainersCount;
        this.maxHeavyContainersCount = maxHeavyContainersCount;
        this.maxElectricContainerCount = maxElectricContainerCount;
        this.maxContainersCount = checkContainerCount(maxContainersCount);
    }

    // Checks if total specified containers count exceed max overall container count
    private int checkContainerCount(int maxContainersCount){
        if(this.maxToxicExplosiveContainersCount + this.maxHeavyContainersCount + this.maxElectricContainerCount > maxContainersCount){
            System.out.println(
                    // beginning set console text color to red, end reset to white
                    "\033[0;31m" +
                            "Warning, ship id: " +
                            this.id +
                            " named: '" +
                            this.name +
                            "' maximum container count exceeds maximum container capacity. \n" +
                            "Please acknowledge setting overall containers count to sum of specified ones: [y]" +
                            "\033[0m"
            );

            Scanner scanner = new Scanner(System.in);
            if(scanner.next().matches(".*")){
                return this.maxToxicExplosiveContainersCount + this.maxHeavyContainersCount + this.maxElectricContainerCount;
            }
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
