package containers.classes;

import containers.interfaces.ElectricInterface;
import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class ChillerContainer extends HeavyContainer implements ElectricInterface {

    public final int maximumWattPowerDraw;

    public ChillerContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial,
            int maximumWattPowerDraw,
            Sender sender
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, armorThickness, containerMaterial, sender);
        this.maximumWattPowerDraw = maximumWattPowerDraw;
    }

    public ChillerContainer() {
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("Safety measures"),
                Evaluators.getArrayListFromInput("Certificates"),
                Evaluators.getIntFromInput("Armor thickness in millimeters"),
                Evaluators.getStringFromInput("Container material")
        );
        this.maximumWattPowerDraw = Evaluators.getIntFromInput("Maximum power draw");
        ConsoleColors.printGreen("Successfully created container!");
    }

    public String toString() {
        return super.toString() +
                ", maximum power draw: " + this.maximumWattPowerDraw;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + ", \n\tmaximumWattPowerDraw: " + this.maximumWattPowerDraw;
    }
}
