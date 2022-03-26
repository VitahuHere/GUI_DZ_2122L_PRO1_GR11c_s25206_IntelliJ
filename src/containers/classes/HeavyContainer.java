package containers.classes;

import utils.Evaluators;

import java.util.ArrayList;

public class HeavyContainer extends StandardContainer {
    public final int armorThickness;
    public final String containerMaterial;

    public HeavyContainer(
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

    public HeavyContainer(){
        super(
                Evaluators.getIntFromInput("Tare"),
                Evaluators.getIntFromInput("Size"),
                Evaluators.getIntFromInput("Cargo Weight"),
                Evaluators.getArrayListFromInput("safety measures"),
                Evaluators.getArrayListFromInput("certificates")
        );
        this.armorThickness = Evaluators.getIntFromInput("Armor thickness");
        this.containerMaterial = Evaluators.getStringInput("StandardContainer material");
    }

    @Override
    public String toString() {
        return super.toString() +
                ", armor thickness: " + this.armorThickness +
                ", container material: " + this.containerMaterial;
    }
}
