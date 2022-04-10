package containers.classes;

import sender.Sender;
import utils.ConsoleColors;
import utils.Evaluators;

import java.util.ArrayList;

public class HeavyContainer extends StandardContainer {
    public final int armorThickness;
    public final String containerMaterial;

    protected HeavyContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates);
        this.armorThickness = armorThickness;
        this.containerMaterial = containerMaterial;
    }

    public HeavyContainer(
            int tare,
            int size,
            int cargoWeight,
            ArrayList<String> safetyMeasures,
            ArrayList<String> certificates,
            int armorThickness,
            String containerMaterial,
            Sender sender
    ) {
        super(tare, size, cargoWeight, safetyMeasures, certificates, sender);
        this.armorThickness = armorThickness;
        this.containerMaterial = containerMaterial;
    }

    public HeavyContainer() {
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates")
        );
        this.armorThickness = Evaluators.getIntFromInput("Armor thickness in millimeters");
        this.containerMaterial = Evaluators.getStringFromInput("StandardContainer material");
        ConsoleColors.printGreen("Successfully created container!");
        ConsoleColors.printYellow(this.toString());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", \narmor thickness: " + this.armorThickness +
                ", \ncontainer material: " + this.containerMaterial;
    }
}
