package containers.classes;

import containers.interfaces.ElectricInterface;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class ChillerContainer extends HeavyContainer implements ElectricInterface {

    // Measured in Watts
    public final int maximumWattPowerDraw;

    public ChillerContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial,
            int maximumWattPowerDraw
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial);
        this.maximumWattPowerDraw = maximumWattPowerDraw;
    }

    public ChillerContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates"),
                Evaluators.getIntFromInput("Armor thickness"),
                Evaluators.getStringInput("container material")
        );
        this.maximumWattPowerDraw = Evaluators.getIntFromInput("Maximum power draw");
        ConsoleColors.printGreen("Successfully created container!");
        ConsoleColors.printYellow(this.toString());
    }

    public String toString(){
        return super.toString() +
                ", \nmaximum power draw: " + this.maximumWattPowerDraw;
    }
}
