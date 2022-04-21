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
                Evaluators.getArrayListFromInput("Safety measures"),
                Evaluators.getArrayListFromInput("Certificates")
        );
        this.armorThickness = Evaluators.getIntFromInput("Armor thickness in millimeters");
        this.containerMaterial = Evaluators.getStringFromInput("Container material");
        ConsoleColors.printGreen("Successfully created container!");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", armor thickness: " + this.armorThickness +
                ", container material: " + this.containerMaterial;
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() +
                ", \n\tarmorThickness: " + this.armorThickness +
                ", \n\tcontainerMaterial: '" + this.containerMaterial + "'";
    }
}
