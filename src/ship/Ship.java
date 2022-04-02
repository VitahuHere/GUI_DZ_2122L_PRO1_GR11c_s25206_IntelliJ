// Written by Cong Minh Vu s25206

package ship;

import app.App;
import containers.abstracts.ToxicAbstract;
import containers.classes.*;
import containers.interfaces.ElectricInterface;
import port.Port;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class Ship {
    public static int shipIndex = 1;

    public final int id;
    public final String name;
    public final String homePort;
    public final int maxCargoWeight;
    public final int maxContainersCount;
    public final int maxToxicExplosiveContainersCount;
    public final int maxHeavyContainersCount;
    public final int maxElectricContainersCount;
    public final ArrayList<StandardContainer> listOfContainers;

    // helper fields
    private int cargoWeight;
    private int toxicExplosiveCounter;
    private int heavyCounter;
    private int electricCounter;
    private int slotsAvailable;


    public String departurePort;
    public String arrivalPort;

    public Ship(
            String name,
            String homePort,
            int maxPayloadWeight,
            int maxContainersCount,
            int maxToxicExplosiveContainersCount,
            int maxHeavyContainersCount,
            int maxElectricContainersCount
    ) {
        this.listOfContainers = new ArrayList<>();
        this.cargoWeight = 0;
        this.toxicExplosiveCounter = 0;
        this.heavyCounter = 0;
        this.electricCounter = 0;

        this.id = shipIndex++;
        this.name = name;
        this.homePort = homePort;
        this.maxCargoWeight = checkPositiveValue(maxPayloadWeight);
        this.maxContainersCount = checkPositiveValue(maxContainersCount);
        this.maxToxicExplosiveContainersCount = maxToxicExplosiveContainersCount;
        this.maxHeavyContainersCount = maxHeavyContainersCount;
        this.maxElectricContainersCount = maxElectricContainersCount;
        this.slotsAvailable = this.maxContainersCount - this.maxToxicExplosiveContainersCount - this.maxHeavyContainersCount - this.maxElectricContainersCount;
    }

    public Ship(){
        ConsoleColors.printYellow("Welcome to Ship creation page!");
        ConsoleColors.printYellow("Please enter following information:");

        this.listOfContainers = new ArrayList<>();
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

        ConsoleColors.printGreen("Successfully created new Ship!");
        ConsoleColors.printYellow(this.toString());
    }

    private int checkPositiveValue(int value){
        if(value < 0){
            ConsoleColors.printRed("Value is negative. Setting to 0");
            return 0;
        }
        return value;
    }

    private int checkSlotsLeft(int value){
        if(this.slotsAvailable <= 0){
            ConsoleColors.printRed("No slots available. Setting value to 0");
            return 0;
        }
        else if(this.slotsAvailable - value < 0){
            ConsoleColors.printRed("Value entered exceed slots available left. Please re-enter: ");
            value = Evaluators.getIntFromInput(this.slotsAvailable);
        }
        this.slotsAvailable -= value;
        ConsoleColors.printBlue(this.slotsAvailable + " slots left");
        return value;
    }

    public void loadContainer(StandardContainer container) {
        boolean addToCargo = false;
        if(container.shipId != -1){
            ConsoleColors.printRed("Container is already taken. Cannot load onto the ship.");
        } else{
            if (this.maxContainersCount <= this.listOfContainers.size() || this.cargoWeight + container.totalWeight > this.maxCargoWeight) {
                ConsoleColors.printRed("Maximum container count or mass reached. Cannot add another one.");
            } else {
                if ((container instanceof ExplosivesContainer || container instanceof ToxicAbstract) && this.toxicExplosiveCounter < this.maxToxicExplosiveContainersCount) {
                    addToCargo = true;
                    this.toxicExplosiveCounter++;
                } else if (container instanceof HeavyContainer && this.heavyCounter < this.maxHeavyContainersCount) {
                    addToCargo = true;
                    this.heavyCounter++;
                } else if (container instanceof ElectricInterface && this.electricCounter < this.maxElectricContainersCount) {
                    addToCargo = true;
                    this.electricCounter++;
                }
                else if(container.getClass().getSimpleName().equals("StandardContainer")){
                    addToCargo = true;
                }
                if (addToCargo) {
                    this.listOfContainers.add(container);
                    this.cargoWeight += container.totalWeight;
                    container.shipId = this.id;
                    App.containers.remove(container);
                }
                else{
                    ConsoleColors.printRed("Reached maximum number of containers of this type. Cannot load more");
                }
            }
        }
    }

    public void offloadContainerToWarehouse(StandardContainer container){
        if(this.listOfContainers.contains(container)){
            if (container instanceof ExplosivesContainer || container instanceof ToxicAbstract) {
                this.toxicExplosiveCounter--;
            } else if (container instanceof HeavyContainer && this.heavyCounter < this.maxHeavyContainersCount) {
                this.heavyCounter--;
            } else if (container instanceof ElectricInterface && this.electricCounter < this.maxElectricContainersCount) {
                this.electricCounter--;
            }
            this.listOfContainers.remove(container);
            this.cargoWeight -= container.totalWeight;
            container.shipId = -1;
            Port.warehouse.addContainer(container);
        }
        else{
            ConsoleColors.printRed("Container is not on this ship. Cannot offload.");
        }
    }

    public int getSlotsAvailable() {
        return slotsAvailable;
    }

    @Override
    public String toString() {
        return "\nShip information: " +
                "id: " + id +
                ", \nname: '" + name + '\'' +
                ", \nhome port: '" + homePort + '\'' +
                ", \nmaximum payload weight: " + maxCargoWeight +
                ", \nmaximum containers count: " + maxContainersCount +
                ", \nmaximum toxic/explosive containers count: " + maxToxicExplosiveContainersCount +
                ", \nmaximum heavy containers count: " + maxHeavyContainersCount +
                ", \nmaximum electric container count: " + maxElectricContainersCount +
                ", \ncargo weight: " + cargoWeight +
                ", \nnumber of standard containers: " + (listOfContainers.size() - toxicExplosiveCounter - heavyCounter - electricCounter) +
                ", \nnumber of toxic/explosive containers: " + toxicExplosiveCounter +
                ", \nnumber of heavy containers: " + heavyCounter +
                ", \nnumber of electric containers: " + electricCounter +
                ", \ndeparture port: " + (departurePort == null ? "n/a" : departurePort) +
                ", \narrival port: " + (arrivalPort == null ? "n/a" : departurePort) +
                ", \ncontainers: " + (listOfContainers.size() == 0 ? "n/a" : listOfContainers);
    }
}
